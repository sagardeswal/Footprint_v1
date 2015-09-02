package com.sacri.footprint_v1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.sacri.footprint_v1.Entities.UserDetails;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PostActivity extends AppCompatActivity {

    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView addImageView;
    private String mCurrentPhotoPath;
    private PostDetails postDetails;
    Intent takePictureIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        addImageView = (ImageView) findViewById(R.id.addImageView);
        dispatchTakePictureIntent();
//        galleryAddPic();
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }



//    private void dispatchTakePictureIntent() {
//        Log.i("com.sacri.footprint_v1","dispatchTakePictureIntent() started");
//        takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        // Ensure that there's a camera activity to handle the intent
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//
//            Log.i("com.sacri.footprint_v1","takePictureIntent.resolveActivity(getPackageManager()) != null");
//            // Create the File where the photo should go
//            File photoFile = null;
//            try {
//                photoFile = createImageFile();
//            } catch (IOException ex) {
//                // Error occurred while creating the File
//                Log.i("com.sacri.footprint_v1", "Exception");
//            }
//            // Continue only if the File was successfully created
//            if (photoFile != null) {
//                Log.i("com.sacri.footprint_v1", "PhotoFile not null");
//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
//                        Uri.fromFile(photoFile));
//                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
//            }
//        }
//    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            addImageView.setImageBitmap(imageBitmap);
        }
    }

//    private File createImageFile() throws IOException {
//        // Create an image file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = "JPEG_" + timeStamp + "_";
//        File storageDir = Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES);
//        File image = null;
//        try {
//            image = File.createTempFile(
//                    imageFileName,  /* prefix */
//                    ".jpg",         /* suffix */
//                    storageDir      /* directory */
//            );
//        }catch(Exception e){
//            e.printStackTrace();
//            throw new IOException(e);
//        }
//
//
//        // Save a file: path for use with ACTION_VIEW intents
//        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
//        Log.i("com.sacri.footprint_v1", "created image file :" + image.getName());
//        Log.i("com.sacri.footprint_v1", "mCurrentPhotoPath :" + mCurrentPhotoPath);
//        return image;
//    }
//
//    private void galleryAddPic() {
//        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//        File f = new File(mCurrentPhotoPath);
//        Uri contentUri = Uri.fromFile(f);
//        mediaScanIntent.setData(contentUri);
//        this.sendBroadcast(mediaScanIntent);
//    }


    //handle the post

    public void postButtonClicked(View view){
//        ServerRequests serverRequests = new ServerRequests(this);
//        serverRequests.storePostDataInBackground(postDetails, new GetPostCallBack() {
//            @Override
//            public void done(PostDetails returnedPostDetails) {
//
//            }
//        });
    }
}
