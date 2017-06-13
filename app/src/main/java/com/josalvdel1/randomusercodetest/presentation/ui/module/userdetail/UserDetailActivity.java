package com.josalvdel1.randomusercodetest.presentation.ui.module.userdetail;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.josalvdel1.randomusercodetest.MyApplication;
import com.josalvdel1.randomusercodetest.R;
import com.josalvdel1.randomusercodetest.di.module.ActivityModule;
import com.josalvdel1.randomusercodetest.domain.entity.User;
import com.josalvdel1.randomusercodetest.presentation.ui.BaseActivity;
import com.josalvdel1.randomusercodetest.util.StringUtils;
import com.josalvdel1.randomusercodetest.util.imageloader.ImageLoader;

import javax.inject.Inject;

import butterknife.BindView;

public class UserDetailActivity extends BaseActivity implements UserDetailPresenter.View {

    public static final String USER_ID_PARAM = "param_user_id";

    @Inject
    UserDetailPresenter presenter;
    @Inject
    ImageLoader imageLoader;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_user_image)
    ImageView ivUserImage;
    @BindView(R.id.tiet_user_gender)
    TextInputEditText tietUserGender;
    @BindView(R.id.til_user_gender)
    TextInputLayout tilUserGender;
    @BindView(R.id.tiet_user_email)
    TextInputEditText tietUserEmail;
    @BindView(R.id.tiet_user_location)
    TextInputEditText tietUserLocation;
    @BindView(R.id.tiet_user_registered)
    TextInputEditText tietUserRegistered;

    private UserDetailViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        viewModel = ViewModelProviders.of(this).get(UserDetailViewModel.class);
        viewModel.bindDataOrigin().observe(this, this::showUser);

        initUI();
        presenter.setView(this);

        String userId = getIntent().getStringExtra(USER_ID_PARAM);
        presenter.init(viewModel, savedInstanceState == null, userId);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_detail;
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    protected void onPause() {
        presenter.pause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        presenter.destroy();
        super.onDestroy();
    }

    private void initUI() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    public void showUser(User user) {
        toolbar.setTitle(StringUtils.getNotNullText(user.getFullName()));
        tietUserEmail.setText(StringUtils.getNotNullText(user.getEmail()));
        tietUserGender.setText(StringUtils.getNotNullText(user.getGender()));

        String largeUserPicture = user.getLargePicture();
        if (largeUserPicture != null) {
            imageLoader.loadWithCircularTransform(largeUserPicture, ivUserImage,
                    ContextCompat.getDrawable(this, R.drawable.ic_user_placeholder));
        }

        tietUserLocation.setText(String.format(getString(R.string.user_location_format),
                user.getStreet(), user.getCity(), user.getState()));

        tietUserRegistered.setText(
                getString(R.string.day_month_year_date_format, user.getRegisteredDate()));
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    public static Intent getIntent(Context context, @NonNull String userId) {
        Intent intent = new Intent(context, UserDetailActivity.class);
        intent.putExtra(USER_ID_PARAM, userId);
        return intent;
    }

    @Override
    public void initializeDagger() {
        super.initializeDagger();
        MyApplication.get(this)
                .getAppComponent().activityComponent(new ActivityModule(this))
                .inject(this);
    }
}
