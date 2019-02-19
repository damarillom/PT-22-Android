package com.dam.iam26509397.pt_22_xat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    boolean left = true;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    String mCurrentPhotoPath;
    static final int REQUEST_TAKE_PHOTO = 1;
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addToScrollView(View view) {
        //added LInearLayout
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.LScroll);

        //added LayoutParams
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = left ? Gravity.LEFT : Gravity.RIGHT;
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        EditText edit = (EditText)findViewById(R.id.editext1);
        String result = edit.getText().toString();
        //add textView
        TextView textView = new TextView(this);
        textView.setText(result);
        //textView.setId(1);
        textView.setLayoutParams(params);
        //textView.setBackground();
        if (left) {
            textView.setBackgroundResource(R.drawable.bocadillol);
            left = false;
        } else {
            textView.setBackgroundResource(R.drawable.bocadillor);
            left = true;
        }

        //added the textView and the Button to LinearLayout
        linearLayout.addView(textView);
    }

    public void addToScrollViewEmoji(View view) {
        //added LInearLayout
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.LScroll);

        //added LayoutParams
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = left ? Gravity.LEFT : Gravity.RIGHT;
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        EditText edit = (EditText)findViewById(R.id.editext1);
        String result = edit.getText().toString();
        //add textView
        TextView textView = new TextView(this);
        textView.setText(result);
        //textView.setId(1);
        textView.setLayoutParams(params);
        //textView.setBackground();
        textView.setBackgroundResource(R.drawable.thinking_face_emoji_peq);
        if (left) {
            //textView.setBackgroundResource(R.drawable.bocadillol);
            left = false;
        } else {
            //textView.setBackgroundResource(R.drawable.bocadillor);
            left = true;
        }

        //added the textView and the Button to LinearLayout
        linearLayout.addView(textView);
    }

    public void addToScrollViewPhoto(View view) {
        //added LInearLayout
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.LScroll);

        //added LayoutParams
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = left ? Gravity.LEFT : Gravity.RIGHT;
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        EditText edit = (EditText)findViewById(R.id.editext1);
        String result = edit.getText().toString();
        //add textView
        ImageView imageView = new ImageView(this);

        if (left) {
            //textView.setBackgroundResource(R.drawable.bocadillol);
            left = false;
        } else {
            //textView.setBackgroundResource(R.drawable.bocadillor);
            left = true;
        }

        //added the textView and the Button to LinearLayout
        mImageView = imageView;
        
        linearLayout.addView(mImageView);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.d("Error: ", "dispatchTakePictureIntent: " + ex);
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = mImageView.getWidth();
        int targetH = mImageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        mImageView.setImageBitmap(bitmap);
    }
}
