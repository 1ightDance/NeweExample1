package com.example.test.bean;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.example.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * file description
 *
 * @author LightDance
 * @date 2019/3/30.
 */
public class Work {
    private static final String TYPE_FILM = "电影";
    private static final String TYPE_GAME = "游戏";
    private static final String TYPE_BOOK = "书籍";
    private static final String TYPE_MUSIC = "音乐";

    private String title;
    private String type;
    private String description;
    private double mark;
    private int imgId;

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

    public Drawable getDrawable(Context context){
        return  context.getResources().getDrawable(getImgId());
    }
}
