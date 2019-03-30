package com.example.test.bean;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.example.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据类，列表中要展示的数据。
 *
 * @author LightDance
 * @date 2019/3/30.
 */
public class Work {
    private static final String TYPE_FILM = "电影";
    private static final String TYPE_GAME = "游戏";
    private static final String TYPE_BOOK = "书籍";
    private static final String TYPE_MUSIC = "音乐";

    /**作品名*/
    private String title;
    /**作品类型，见{@link #TYPE_BOOK},{@link #TYPE_GAME},{@link #TYPE_FILM},{@link #TYPE_MUSIC}*/
    private String type;
    /**描述*/
    private String description;
    /**评价*/
    private double mark;
    /**图片资源id，以便将图片加载出来*/
    private int imgId;

    /**构造方法，将值传进来*/
    public Work(String title, String type, String description, double mark, int imgId) {
        this.title = title;
        this.type = type;
        this.description = description;
        this.mark = mark;
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    /**static类型的获取默认数据方式，由于没学数据库和网络编程，先这样*/
    public static List<Work> getDefaultWorks(Context context){
        Resources res = context.getResources();
        List<Work> list = new ArrayList<>();
        list.add(new Work("怦然心动" , TYPE_FILM , res.getString(R.string.description_film_1) , 8.0 , R.drawable.film_1));
        list.add(new Work("守望者" , TYPE_FILM , res.getString(R.string.description_film_2) , 9.0 , R.drawable.film_2));
        list.add(new Work("钢琴家" , TYPE_FILM , res.getString(R.string.description_film_3) , 9.0 , R.drawable.film_3));
        list.add(new Work("怦然心动" , TYPE_FILM , res.getString(R.string.description_film_1) , 8.0 , R.drawable.film_1));
        list.add(new Work("怦然心动" , TYPE_FILM , res.getString(R.string.description_film_1) , 8.0 , R.drawable.film_1));
        list.add(new Work("怦然心动" , TYPE_FILM , res.getString(R.string.description_film_1) , 8.0 , R.drawable.film_1));
        list.add(new Work("怦然心动" , TYPE_FILM , res.getString(R.string.description_film_1) , 8.0 , R.drawable.film_1));
        list.add(new Work("怦然心动" , TYPE_FILM , res.getString(R.string.description_film_1) , 8.0 , R.drawable.film_1));
        return list;
    }

    /**为了方便加载图片(因为加载图片时需要一个Drawable类型参数，所以加一个辅助方法)*/
    public Drawable getDrawable(Context context){
        //通过上下文context参数获取app资源Resources，从而能找到放图片文件的地方
        return  context.getResources().getDrawable(getImgId());
    }
}
