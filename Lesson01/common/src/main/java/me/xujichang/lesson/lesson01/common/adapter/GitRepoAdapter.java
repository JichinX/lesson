package me.xujichang.lesson.lesson01.common.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import me.xujichang.lesson.lesson01.common.R;
import me.xujichang.lesson.lesson01.common.bean.GitRepo;

/**
 * @ProjectName: Lesson01
 * @Package: me.xujichang.lesson.lesson01.common.adapter
 * @ClassName: GitRepoAdapter
 * @Description: java类作用描述
 * @Author: boss
 * @CreateDate: 2019-07-03 09:48
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019-07-03 09:48
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class GitRepoAdapter extends RecyclerView.Adapter<GitRepoAdapter.Holder> {
    private List<GitRepo> mGitRepos;

    public GitRepoAdapter(List<GitRepo> vGitRepos) {
        mGitRepos = vGitRepos;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.common_adapter_git_repo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.bindData(mGitRepos.get(position));
    }

    @Override
    public int getItemCount() {
        return mGitRepos.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvDes;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_repo_name);
            tvDes = itemView.findViewById(R.id.tv_des);
        }

        public void bindData(GitRepo vGitRepo) {
            tvName.setText(vGitRepo.getName());
            tvDes.setText(vGitRepo.getDescription());
        }
    }
}
