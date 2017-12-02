package com.example.android.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import static android.R.attr.data;
import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {
    private NewsAdapter madapter;
    private static int LOADER_ID = 0;
    ProgressBar mprogress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        ListView newslist = (ListView) findViewById(R.id.list_view);
        madapter = new NewsAdapter(this);
        mprogress = (ProgressBar) findViewById(R.id.progress_bar);
        if (info != null && info.isConnected()) {
            newslist.setAdapter(madapter);
            newslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    News news = madapter.getItem(i);
                    Uri NewsUri = Uri.parse(news.geturl());
                    Intent websiteIntent = new Intent(Intent.ACTION_VIEW, NewsUri);
                    startActivity(websiteIntent);
                }
            });
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(LOADER_ID, null, this);
        } else {
            TextView blank = (TextView) findViewById(R.id.blank_text);
            blank.setText("NO INTERNET CONNECTION");
            mprogress.setVisibility(GONE);
        }
    }

    @Override
    public android.content.Loader<List<News>> onCreateLoader(int id, Bundle args) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .encodedAuthority("content.guardianapis.com")
                .appendPath("search")
                .appendQueryParameter("order-by", "newest")
                .appendQueryParameter("show-references", "author")
                .appendQueryParameter("show-tags", "contributor")
                .appendQueryParameter("q", "Android")
                .appendQueryParameter("api-key", "test");
        String url = builder.build().toString();
        return new NewsLoader(this, url);
    }

    @Override
    public void onLoadFinished(android.content.Loader<List<News>> loader, List<News> newses) {
        if (newses != null) {
            mprogress.setVisibility(GONE);
            madapter.setNotifyOnChange(false);
            madapter.clear();
            madapter.setNotifyOnChange(true);
            madapter.addAll(newses);
        }
    }

    @Override
    public void onLoaderReset(android.content.Loader<List<News>> loader) {

    }
}
