package com.sacri.footprint_v1;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class TakePhotoActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView viewPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);

        Button takePhotoButton = (Button) findViewById(R.id.takePhotoButton);
        viewPhoto = (ImageView) findViewById(R.id.viewPhoto);

        //Disable Button if user has no camera
        if(!hasCamera()){
            takePhotoButton.setEnabled(false);
        }
    }

    private boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }


    //Launch Camera
    public void launchCameraApp(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //take a picture and pass results along to onActivityResult
        startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);

    }

    //return the image taken
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            //get photo
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            viewPhoto.setImageBitmap(photo);
        }
    }


}
