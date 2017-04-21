package com.official.news.menu;

/**
 * Created by nabin on 8/11/2015.
 */
public class NavDrawerItems {

    private String title;
    private int icon;

    public NavDrawerItems(String title, int icon){
        this.title = title;
        this.icon = icon;
    }
    public NavDrawerItems(String title){
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    public int getIcon(){
        return this.icon;
    }



}