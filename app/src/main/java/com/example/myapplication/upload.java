package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class upload extends AppCompatActivity {

    ImageView uploadImg;

    final int IMAGE_REQUEST=70;
    Uri imageLocationPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        uploadImg = findViewById(R.id.u_image);
    }

    public void selectImage(View view){

        try{
            Intent objectIntent = new Intent();
            objectIntent.setType("image/*");

            objectIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(objectIntent,IMAGE_REQUEST);

        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try{

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imageLocationPath=data.getData();
            Bitmap objectBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageLocationPath);

            uploadImg.setImageBitmap(objectBitmap);
        }
    }
        catch (Exception e)
        {
        Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}