package com.sacri.footprint_v1.Entities;

import java.sql.Blob;

/**
 * Created by bazinga on 01/09/15.
 */
public class ImageDetails {

    private Blob postImage;
    private String geoTag;

    public ImageDetails(String geoTag, Blob postImage) {
        this.geoTag = geoTag;
        this.postImage = postImage;
    }

    public String getGeoTag() {
        return geoTag;
    }

    public void setGeoTag(String geoTag) {
        this.geoTag = geoTag;
    }

    public Blob getPostImage() {
        return postImage;
    }

    public void setPostImage(Blob postImage) {
        this.postImage = postImage;
    }
}
