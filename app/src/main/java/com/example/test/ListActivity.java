package com.example.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
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

    /**recyclerview*/
    private RecyclerView mRvWorkList;
    /**添加新纪录功能按钮，还没实现功能*/
    private FloatingActionButton mFabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //绑定id
        mRvWorkList = findViewById(R.id.rv_work_list);
        mFabAdd = findViewById(R.id.fab_add);
        //为recyclerview加载数据
        initRecyclerView();
    }

    private void initRecyclerView() {
        //获取数据
        List<Work> works = Work.getDefaultWorks(this);
        Adapter adapter = new Adapter(works);
        mRvWorkList.setAdapter(adapter);
        //设置排列方式
        mRvWorkList.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.VERTICAL , false));
    }

    /**适配器，为了方便就把这个类写在ListActivity里面了，如果这个适配器只在这个类里面使用可以这么干，如果
     * 在其他Activity中也使用过，那么可以考虑新建一个class放在新建的class里面
     *
     * 用来将List<Work>中存放的数据加载到ViewHolder所管理的itemView中，从而能让recyclerView显示数据
     *
     */
    class Adapter extends RecyclerView.Adapter<Holder> {
        private List<Work> mList;
        /**构造方法，加载数据进来*/
        Adapter(List<Work> workList) {
            this.mList = workList;
        }

        /**如何创建viewHolder以使用其管理的itemview*/
        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(ListActivity.this);
            return new Holder(inflater, viewGroup);
        }
        /**如何将数据传递给之前创建好的ViewHolder*/
        @Override
        public void onBindViewHolder(@NonNull Holder holder, int i) {
            //获取list列表中的数据
            Work work = mList.get(i);
            holder.bind(work);
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
        //设置数据集合的方法，比如刚看过一部电影，把它加入到list中后想展示查出来，
        // 可以用这个方法重新设置数据集，然后刷新recyclerview列表
        void setDataSet(List<Work> list) {
            mList = list;
        }
    }

    /**
     * ViewHolder类，写在这里的原因同{@link Adapter}，管理itemview的类
     */
    class Holder extends RecyclerView.ViewHolder {
        TextView mTvTitle;
        TextView mTvMark;
        TextView mTvType;
        TextView mTvDescription;
        ImageView mImgPoster;

        /**由adapter传入的数据类*/
        Work mWork;

        /**本来只需要一个参数(父类的初始化方法)，这时候需要在外部(点一下方法名)设置好布局信息后再传给Holder，
         * 但加载recyclerView中每一条目 的布局文件应当是Holder的职能，因此移动到内部
         */
        Holder(LayoutInflater inflater, ViewGroup parent) {
            //实例化一个view      inflate：使充气（于轮胎、气球等）; （使） 膨胀;
            super(inflater.inflate(R.layout.item_work_abstract , parent, false));
            //绑定控件
            mTvDescription = itemView.findViewById(R.id.tv_work_description);
            mTvMark = itemView.findViewById(R.id.tv_work_mark);
            mTvTitle = itemView.findViewById(R.id.tv_work_title);
            mTvType = itemView.findViewById(R.id.tv_work_type);
            mImgPoster = itemView.findViewById(R.id.img_work);
            //设置点击监听，比如要想点击某个条目之后跳转到该条目中，可以在这里添加跳转逻辑
            itemView.setOnClickListener(v -> Toast.makeText(ListActivity.this, "这里是" + mWork.getTitle(), Toast.LENGTH_SHORT).show());
        }

        /**将数据填写到视图对应位置的方法，在适配器Adapter类的{@link Adapter#onBindViewHolder}中被调用*/
        void bind(Work work) {
            mWork = work;
            mTvTitle.setText(mWork.getTitle());
            mTvMark.setText(String.valueOf(mWork.getMark()));
            mTvDescription.setText(mWork.getDescription());
            mTvType.setText(mWork.getType());
            //加载图片的方式
            mImgPoster.setImageDrawable(mWork.getDrawable(ListActivity.this));
        }
    }
}
