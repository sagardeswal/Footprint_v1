package com.sacri.footprint_v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;


public class Feed extends AppCompatActivity {

    private Button postButton;
    private ListView feedListView;
    private PostDetails[] postDetailsArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        feedListView = (ListView) findViewById(R.id.feedListView);
        postButton = (Button) findViewById(R.id.postButton);

        ListAdapter postAdaptor = new CustomAdaptor(this, postDetailsArray);
        feedListView.setAdapter(postAdaptor);
    }

    public void postButtonClicked(View view){
        startActivity(new Intent(this, PostActivity.class));
    }
}
