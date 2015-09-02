package com.sacri.footprint_v1;

import com.sacri.footprint_v1.Entities.ImageDetails;

import java.sql.Blob;

/**
 * Created by bazinga on 01/09/15.
 */
public class PostDetails {

    public PostDetails(ImageDetails image, String postText, String privacy) {
        this.image = image;
        this.postText = postText;
        this.privacy = privacy;
    }

    private String postText;
    private ImageDetails image;
    private String privacy;

    public ImageDetails getImage() {
        return image;
    }

    public void setImage(ImageDetails image) {
        this.image = image;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }
}
