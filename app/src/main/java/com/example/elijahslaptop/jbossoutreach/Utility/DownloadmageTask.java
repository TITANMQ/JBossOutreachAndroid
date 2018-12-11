package com.example.elijahslaptop.jbossoutreach.Utility;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class DownloadmageTask extends AsyncTask<String,Void, Bitmap> {


    private ImageView imageView;
    public DownloadmageTask(ImageView imageView){
        this.imageView = imageView;
    }
    @Override
    protected Bitmap doInBackground(String... urls) {
        String urlOfImage = urls[0];
        Bitmap image = null;

        try {
            InputStream inputStream = new URL(urlOfImage).openStream();
            image = BitmapFactory.decodeStream(inputStream);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
