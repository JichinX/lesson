package me.xujichang.lesson.lesson01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import me.xujichang.lesson.lesson01.common.GitApi;
import me.xujichang.lesson.lesson01.common.bean.GitRepo;
import me.xujichang.lesson.lesson01.common.net.RetrofitUtil;
import me.xujichang.lesson.lesson01.common.widget.GitReposListView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "";
    private GitReposListView mListView;
    private SwipeRefreshLayout mRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        refreshData();
    }

    private void initView() {
        mListView = findViewById(R.id.git_repos_list);
        mRefreshLayout = findViewById(R.id.refresh_view);

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新数据
                refreshData();
            }
        });
    }

    private void refreshData() {
        if (!mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(true);
        }
        RetrofitUtil
                .createRetrofit(GitApi.BASE_URL)
                .create(GitApi.class)
                .loadRepos("xujichang")
                .enqueue(new Callback<List<GitRepo>>() {
                    @Override
                    public void onResponse(Call<List<GitRepo>> call, Response<List<GitRepo>> response) {
                        if (null == response.body()) {

                        }
                        stopRefresh();
                        mListView.setGitRepos(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<GitRepo>> call, Throwable t) {
                        Log.i(TAG, "onFailure: ");
                        t.printStackTrace();
                        stopRefresh();
                    }
                });
    }

    private void stopRefresh() {
        mRefreshLayout.setRefreshing(false);
    }
}
