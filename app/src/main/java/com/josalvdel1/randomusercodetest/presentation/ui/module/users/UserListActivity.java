package com.josalvdel1.randomusercodetest.presentation.ui.module.users;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        initUI();
        presenter.setView(this);
        presenter.init();
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

    @Override
    public void showUsers(List<User> users) {
        userListAdapter.addAll(users);
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
