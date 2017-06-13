package com.josalvdel1.randomusercodetest.presentation.ui.module.users;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.josalvdel1.randomusercodetest.MyApplication;
import com.josalvdel1.randomusercodetest.R;
import com.josalvdel1.randomusercodetest.di.module.ActivityModule;
import com.josalvdel1.randomusercodetest.domain.entity.User;
import com.josalvdel1.randomusercodetest.presentation.ui.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class UserListActivity extends BaseActivity implements UserListPresenter.View {

    @Inject
    UserListPresenter presenter;
    @Inject
    UserListAdapter userListAdapter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_users)
    RecyclerView rvUsers;
    @BindView(R.id.srl_loading)
    SwipeRefreshLayout srlLoading;

    private UserListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        viewModel = ViewModelProviders.of(this).get(UserListViewModel.class);
        viewModel.bindDataOrigin().observe(this, this::showUsers);

        initUI();
        presenter.setView(this);
        presenter.init(viewModel, savedInstanceState == null);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_list;
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
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());

        srlLoading.setEnabled(false);

        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        rvUsers.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                layoutManager.getOrientation());
        rvUsers.addItemDecoration(dividerItemDecoration);

        rvUsers.setHasFixedSize(true);
        rvUsers.setAdapter(userListAdapter);
    }

    public void showUsers(List<User> users) {
        userListAdapter.addAll(users);
    }

    @OnClick(R.id.fab_load_more)
    public void onLoadMoreClicked() {
        presenter.onLoadMoreClicked();
    }

    @Override
    public void showLoading() {
        srlLoading.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        srlLoading.setRefreshing(false);
    }

    @Override
    public void showEmptyView() {
    }

    @Override
    public void showGenericError() {
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, UserListActivity.class);
    }

    @Override
    public void initializeDagger() {
        super.initializeDagger();
        MyApplication.get(this)
                .getAppComponent().activityComponent(new ActivityModule(this))
                .inject(this);
    }
}
