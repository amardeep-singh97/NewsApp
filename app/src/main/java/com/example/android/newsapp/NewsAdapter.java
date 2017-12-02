package com.example.android.newsapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.resource;

/**
 * Created by Amardeep on 7/23/2017.
 */

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(Context context) {
        super(context, -1, new ArrayList<News>());
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemview = convertView;
        if (listitemview == null) {
            listitemview = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        News currentnews = getItem(position);
        TextView headline = (TextView) listitemview.findViewById(R.id.news_headline);
        headline.setText(currentnews.getheadline());
        TextView publisher = (TextView) listitemview.findViewById(R.id.publisher_name);
        publisher.setText(currentnews.getpublisher());
        TextView date = (TextView) listitemview.findViewById(R.id.date);
        date.setText(currentnews.getDate());
        return listitemview;
    }
}
