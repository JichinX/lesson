package me.xujichang.lesson.lesson01.common;

import java.util.List;

import me.xujichang.lesson.lesson01.common.bean.GitRepo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @ProjectName: Lesson01
 * @Package: me.xujichang.lesson.lesson01.common
 * @ClassName: GitApi
 * @Description: java类作用描述
 * @Author: boss
 * @CreateDate: 2019-07-03 09:59
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019-07-03 09:59
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public interface GitApi {
    String BASE_URL = "https://api.github.com/";

    @GET("users/{name}/repos")
    Call<List<GitRepo>> loadRepos(@Path("name") String userName);

}
