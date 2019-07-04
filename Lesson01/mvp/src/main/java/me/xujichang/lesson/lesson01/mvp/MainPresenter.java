package me.xujichang.lesson.lesson01.mvp;

import java.util.List;

import me.xujichang.lesson.lesson01.common.GitApi;
import me.xujichang.lesson.lesson01.common.bean.GitRepo;
import me.xujichang.lesson.lesson01.common.net.RetrofitUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @ProjectName: Lesson01
 * @Package: me.xujichang.lesson.lesson01.mvp
 * @ClassName: MainPresenter
 * @Description: java类作用描述
 * @Author: boss
 * @CreateDate: 2019-07-04 13:45
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019-07-04 13:45
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mView;

    public MainPresenter(MainContract.View vView) {
        mView = vView;
    }

    @Override
    public void loadRepos(String name) {
        mView.startLoading("加载中...");
        RetrofitUtil
                .createRetrofit(GitApi.BASE_URL)
                .create(GitApi.class)
                .loadRepos("xujichang")
                .enqueue(new Callback<List<GitRepo>>() {
                    @Override
                    public void onResponse(Call<List<GitRepo>> call, Response<List<GitRepo>> response) {
                        if (null == response.body()) {
                            mView.loadingError("数据为空...");
                        }
                        mView.stopLoading();
                        mView.onLoadRepos(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<GitRepo>> call, Throwable t) {
                        t.printStackTrace();
                        mView.stopLoading();
                    }
                });
    }
}
