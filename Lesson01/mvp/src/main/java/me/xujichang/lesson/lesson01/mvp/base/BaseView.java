package me.xujichang.lesson.lesson01.mvp.base;

/**
 * @ProjectName: Lesson01
 * @Package: me.xujichang.lesson.lesson01.mvp.base
 * @ClassName: BaseView
 * @Description: java类作用描述
 * @Author: boss
 * @CreateDate: 2019-07-04 13:46
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019-07-04 13:46
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public interface BaseView {
    void startLoading(String msg);

    void stopLoading();

    void loadingError(String msg);
    //...
}
