package com.sacri.footprint_v1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


class CustomAdaptor extends ArrayAdapter<PostDetails> {

    public CustomAdaptor(Context context, PostDetails[] postDetailsArray) {
        super(context, R.layout.custom_row, postDetailsArray);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater foodInflater = LayoutInflater.from(getContext());
        View customView = foodInflater.inflate(R.layout.custom_row, parent, false);

        PostDetails postDetails = getItem(position);
        TextView profileText = (TextView) customView.findViewById(R.id.postTextView);
        ImageView profileImageView = (ImageView) customView.findViewById(R.id.postImageView);

        profileText.setText(postDetails.getPostText());
//        profileImageView.setImageBitmap(postDetails.getImage().getPostImage());

        return customView;
    }
}
