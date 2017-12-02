package com.example.android.newsapp;

/**
 * Created by Amardeep on 7/23/2017.
 */

public class News {
    private String mheadline;
    private String murl;
    private String mpublisher;
    private String mDate;

    public News(String headline, String publisher, String url, String Date) {
        mheadline = headline;
        murl = url;
        mpublisher = publisher;
        mDate = Date;
    }

    public String getheadline() {
        return mheadline;
    }

    public String geturl() {
        return murl;
    }

    public String getpublisher() {
        return mpublisher;
    }

    public String getDate() {
        return mDate;
    }
}
