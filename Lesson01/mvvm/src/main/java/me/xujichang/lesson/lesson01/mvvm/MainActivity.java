package me.xujichang.lesson.lesson01.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import java.util.List;

import me.xujichang.lesson.lesson01.common.bean.GitRepo;
import me.xujichang.lesson.lesson01.mvvm.databinding.ActivityMainBinding;
import me.xujichang.lesson.lesson01.mvvm.databinding.ActivityMainBindingImpl;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mViewModel;
    private List<GitRepo> mGitRepos;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //一
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mViewModel.subscribeGitrepos().observe(this, new Observer<List<GitRepo>>() {
            @Override
            public void onChanged(List<GitRepo> vGitRepos) {
                mBinding.gitReposList.setGitRepos(vGitRepos);
            }
        });
        mViewModel.subscribeStatus().observe(this, new Observer<LoadingStatus>() {
            @Override
            public void onChanged(LoadingStatus vLoadingStatus) {
                switch (vLoadingStatus) {
                    case STOP:
                    case ERROR:
                        mBinding.refreshView.setRefreshing(false);
                        break;
                    case START:
                        mBinding.refreshView.setRefreshing(true);
                        break;
                    default:
                }
            }
        });
        mBinding.refreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });
        refreshData();
    }

    private void refreshData() {
        mViewModel.loadrepos("xujichang");
    }
}
