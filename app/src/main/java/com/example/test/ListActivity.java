package com.example.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.bean.Work;

import java.util.List;

/**
 * @author LightDance
 */
public class ListActivity extends AppCompatActivity {

    private RecyclerView mRvWorkList;
    private FloatingActionButton mFabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mRvWorkList = findViewById(R.id.rv_work_list);
        mFabAdd = findViewById(R.id.fab_add);
        initRecyclerView();
    }

    private void initRecyclerView() {
        List<Work> works = Work.getDefaultWorks(this);
        Adapter adapter = new Adapter(works);
        mRvWorkList.setAdapter(adapter);
        mRvWorkList.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.VERTICAL , false));
    }

    class Adapter extends RecyclerView.Adapter<Holder> {
        private List<Work> mList;

        Adapter(List<Work> workList) {
            this.mList = workList;
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(ListActivity.this);
            return new Holder(inflater, viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int i) {
            Work work = mList.get(i);
            holder.bind(work);
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        void setDataSet(List<Work> list) {
            mList = list;
        }
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView mTvTitle;
        TextView mTvMark;
        TextView mTvType;
        TextView mTvDescription;
        ImageView mImgPoster;

        Work mWork;

        /**本来需要一个参数，这时候需要在外部(点一下方法名)设置布局信息，会让代码耦合度增加，因此移动到内部*/
        Holder(LayoutInflater inflater, ViewGroup parent) {
            //实例化一个view      inflate：使充气（于轮胎、气球等）; （使） 膨胀;
            super(inflater.inflate(R.layout.item_work_abstract , parent, false));
            mTvDescription = itemView.findViewById(R.id.tv_work_description);
            mTvMark = itemView.findViewById(R.id.tv_work_mark);
            mTvTitle = itemView.findViewById(R.id.tv_work_title);
            mTvType = itemView.findViewById(R.id.tv_work_type);
            mImgPoster = itemView.findViewById(R.id.img_work);

            itemView.setOnClickListener(v -> Toast.makeText(ListActivity.this, "这里是" + mWork.getTitle(), Toast.LENGTH_SHORT).show());
        }

        void bind(Work work) {
            mWork = work;
            mTvTitle.setText(mWork.getTitle());
            mTvMark.setText(String.valueOf(mWork.getMark()));
            mTvDescription.setText(mWork.getDescription());
            mTvType.setText(mWork.getType());
            mImgPoster.setImageDrawable(mWork.getDrawable(ListActivity.this));
        }
    }
}
