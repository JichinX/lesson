package me.xujichang.lesson.lesson01.mvp;

import android.os.Bundle;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import me.xujichang.lesson.lesson01.common.bean.GitRepo;
import me.xujichang.lesson.lesson01.common.widget.GitReposListView;
import me.xujichang.lesson.lesson01.mvp.base.BaseActivity;

public class MainActivity extends BaseActivity implements MainContract.View {

    private SwipeRefreshLayout mRefreshLayout;
    private GitReposListView mListView;
    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mPresenter = new MainPresenter(this);

        mListView = findViewById(R.id.git_repos_list);
        mRefreshLayout = findViewById(R.id.refresh_view);

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新数据
                refreshData();
            }
        });
        refreshData();
    }

    private void refreshData() {
        mPresenter.loadRepos("xujichang");
    }

    @Override
    public void onLoadRepos(List<GitRepo> vGitRepos) {
        mListView.setGitRepos(vGitRepos);
    }

    @Override
    public void startLoading(String msg) {
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    public void stopLoading() {
        mRefreshLayout.setRefreshing(false);
    }
}
