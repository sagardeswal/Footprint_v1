package com.sacri.footprint_v1;


/**
 * Created by bazinga on 01/09/15.
 */
public interface GetPostCallBack {

    interface GetUserCallback {
        public abstract void done(PostDetails returnedPostDetails);
    }
}
