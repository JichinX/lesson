package me.xujichang.lesson.lesson01.common.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ProjectName: Lesson01
 * @Package: me.xujichang.lesson.lesson01.common.net
 * @ClassName: RetrofitUtil
 * @Description: java类作用描述
 * @Author: boss
 * @CreateDate: 2019-07-03 09:08
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019-07-03 09:08
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class RetrofitUtil {

    public static Retrofit createRetrofit(String vBaseUrl) {
        return new Retrofit
                .Builder()
                .client(createClient())
                .baseUrl(vBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static OkHttpClient createClient() {
        HttpLoggingInterceptor loInterceptor = new HttpLoggingInterceptor();
        loInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient
                .Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(loInterceptor).build();
    }
}
