package me.xujichang.lesson.lesson01.mvp;

import java.util.List;

import me.xujichang.lesson.lesson01.common.bean.GitRepo;
import me.xujichang.lesson.lesson01.mvp.base.BasePresenter;
import me.xujichang.lesson.lesson01.mvp.base.BaseView;

/**
 * @ProjectName: Lesson01
 * @Package: me.xujichang.lesson.lesson01.mvp
 * @ClassName: MainContract
 * @Description: java类作用描述
 * @Author: boss
 * @CreateDate: 2019-07-04 13:47
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019-07-04 13:47
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class MainContract {
    public interface Presenter extends BasePresenter {
        void loadRepos(String name);

    }

    public interface View extends BaseView {
        void onLoadRepos(List<GitRepo> vGitRepos);
    }
}
