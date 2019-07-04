package me.xujichang.lesson.lesson01.mvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import me.xujichang.lesson.lesson01.common.GitApi;
import me.xujichang.lesson.lesson01.common.bean.GitRepo;
import me.xujichang.lesson.lesson01.common.net.RetrofitUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @ProjectName: Lesson01
 * @Package: me.xujichang.lesson.lesson01.mvvm
 * @ClassName: MainViewModel
 * @Description: java类作用描述
 * @Author: boss
 * @CreateDate: 2019-07-04 14:57
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019-07-04 14:57
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class MainViewModel extends AndroidViewModel {
    private MutableLiveData<List<GitRepo>> mLiveData;
    private MutableLiveData<LoadingStatus> mStatusLiveData;

    public MainViewModel(@NonNull Application application) {
        super(application);
        mLiveData = new MutableLiveData<>();
        mStatusLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<GitRepo>> subscribeGitrepos() {
        return mLiveData;
    }

    public MutableLiveData<LoadingStatus> subscribeStatus() {
        return mStatusLiveData;
    }

    public void loadrepos(String name) {
        mStatusLiveData.setValue(LoadingStatus.START);
        RetrofitUtil
                .createRetrofit(GitApi.BASE_URL)
                .create(GitApi.class)
                .loadRepos("xujichang")
                .enqueue(new Callback<List<GitRepo>>() {
                    @Override
                    public void onResponse(Call<List<GitRepo>> call, Response<List<GitRepo>> response) {
                        if (null == response.body()) {
                            mStatusLiveData.setValue(LoadingStatus.ERROR);
//                            mView.loadingError("数据为空...");
                        }
                        mLiveData.setValue(response.body());
                        mStatusLiveData.setValue(LoadingStatus.STOP);
//                        mView.stopLoading();
//                        mView.onLoadRepos(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<GitRepo>> call, Throwable t) {
                        t.printStackTrace();
                        mStatusLiveData.setValue(LoadingStatus.ERROR);
//                        mView.stopLoading();
                    }
                });
    }
}
