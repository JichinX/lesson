package me.xujichang.lesson.lesson01.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.xujichang.lesson.lesson01.common.R;
import me.xujichang.lesson.lesson01.common.adapter.GitRepoAdapter;
import me.xujichang.lesson.lesson01.common.bean.GitRepo;

/**
 * @ProjectName: Lesson01
 * @Package: me.xujichang.lesson.lesson01.common.widget
 * @ClassName: GitReposListView
 * @Description: java类作用描述
 * @Author: boss
 * @CreateDate: 2019-07-03 09:13
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019-07-03 09:13
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class GitReposListView extends FrameLayout {
    private RecyclerView mRecyclerView;
    private GitRepoAdapter mRepoAdapter;
    private List<GitRepo> mRepoList;

    public GitReposListView(@NonNull Context context) {
        this(context, null);
    }

    public GitReposListView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GitReposListView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context vContext, AttributeSet vAttrs, int vDefStyleAttr) {
        LayoutInflater.from(vContext).inflate(R.layout.common_layout_git_repos, this);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mRepoList = new ArrayList<>();
        mRepoAdapter = new GitRepoAdapter(mRepoList);

        mRecyclerView.setAdapter(mRepoAdapter);
    }

    public void setGitRepos(List<GitRepo> vBody) {
        mRepoList.clear();
        mRepoList.addAll(vBody);
        mRepoAdapter.notifyDataSetChanged();
    }
}
