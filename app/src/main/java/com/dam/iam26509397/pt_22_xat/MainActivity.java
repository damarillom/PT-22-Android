package com.dam.iam26509397.pt_22_xat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    boolean left = true;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final String TAG = "test";
    static final int REQUEST_IMAGE_CAPTURE2 = 2;
    String mCurrentPhotoPath;
    static final int REQUEST_TAKE_PHOTO = 1;
    ImageView mImageView;
    private static final int MY_PERMISSIONS_REQUESTS = 70;
    //    File sampleDir = Environment.getExternalStorageDirectory();
    Boolean bGranted = false;
    private Camera camera;

    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            int cameraId;

            cameraId = findFrontFacingCamera();
            if (cameraId < 0) {
                Toast.makeText(this, "No front facing camera found.",
                        Toast.LENGTH_LONG).show();
            } else {
                camera = null; //Camera.open(cameraId);
                //Log.d(TAG, "onCreate: "+String.valueOf(cameraId));
            }
            Log.d("test", "ok");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "onCreate: "+e.getCause()+e.getMessage());
        }
        checkPermissions();
    }

    private int findFrontFacingCamera() {
        int cameraId = -1;
        // Search for the front facing camera
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                //Log.d("test", "Camera found"+cameraId);
                cameraId = i;
                break;
            }
        }
        return cameraId;
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
        //textView.setText(result);
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


    @SuppressLint("NewApi")
    public void addToScrollViewPhoto(View view) {
        //added LInearLayout
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.LScroll);

        //added LayoutParams
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = left ? Gravity.LEFT : Gravity.RIGHT;
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        mImageView = new ImageView(this);
        if (checkSelfPermission(Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA},
                    MY_CAMERA_PERMISSION_CODE);
        } else {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }


        if (left) {
            //textView.setBackgroundResource(R.drawable.bocadillol);
            left = false;
        } else {
            //textView.setBackgroundResource(R.drawable.bocadillor);
            left = true;
        }

        //added the textView and the Button to LinearLayout
        linearLayout.addView(mImageView);

    }
    /**public void takePhoto() {
        try {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                File photoFile = null;
                try {
                    photoFile = createImageFile(false);
                } catch (Exception e) {
                    // Error occurred while creating the File
                    e.printStackTrace();
                    Log.d(TAG, "takePhoto1: "+ e.getCause()                +e.getMessage());
                }
                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(this,
                            "com.dam.eva.pt22xat.fileprovider", photoFile);

                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    Log.d(TAG, "takePhoto2: " + photoURI + "\n"+ mCurrentPhotoPath);

                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "test: "+e.getMessage()+e.getCause());
        }
    }*/

    private File createImageFile(boolean Video) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File storageVids=getExternalFilesDir(Environment.DIRECTORY_MOVIES);
        //privades, en desinstal. es perden
        File image;
        if (!Video==true) {
            String imageFileName = "JPEG_" + timeStamp + "_";

            //File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            //https://developer.android.com/training/camera/photobasics
            //File file=new File(storageDir,"pic.jpg");
            image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );
            // Save a file: path for use witgetExternalStorageDirectoryh ACTION_VIEW intents
            mCurrentPhotoPath = image.getAbsolutePath();

            //Log.d(TAG, "createImageFile: " + mCurrentPhotoPath + "\n"+ sampleDir.getAbsolutePath());
        }
        else {
            //File sampleDir = Environment.getExternalStorageDirectory(); //storage/emulated/0
            //Log.d(TAG, "createImageFile: " + sampleDir ); //mCurrentPhotoPath);

            String imageFileName = "VID_" + timeStamp + "_";
            image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".mp4",         /* suffix */
                    storageVids      /* directory */
            );
            mCurrentPhotoPath = image.getAbsolutePath();
            //Log.d(TAG, "createImageFile: " + sampleDir ); //mCurrentPhotoPath);

        }

        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            mImageView.setImageBitmap(photo);
        }
    }


    /**private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile(false);
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.d("Error: ", "dispatchTakePictureIntent: " + ex);
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                /**Uri photoURI = FileProvider.getUriForFile(this,
                        "com.dam.iam26509397.pt_22_xat",
                        photoFile);*/

                /**takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }*/

    /**private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }*/

    /**private void setPic() {
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
    }*/

    public void checkPermissions() {


        int permCheck1 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        int permCheck2 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);


        // Log.d(TAG, "permCheck és:" + ", " + String.valueOf(permCheck)+","+String.valueOf(permCheck1));


        if (!(permCheck1 == PackageManager.PERMISSION_GRANTED) | (!(permCheck2 == PackageManager.PERMISSION_GRANTED)))
        {

            // request the permission.
            // CALLBACK_NUMBER is a integer constants
            //Toast.makeText(this, "demana permis, no rationale ", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, MY_PERMISSIONS_REQUESTS);
            // The callback method gets the result of the request.
            Log.d(TAG, " no rationale");

            //txtInfo.setText("");

        } else bGranted = true;


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new
                        Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }
    }

    //Esta función nos permite mandar otro mensaje como si fuesemos el mismo usuario en lugar de ser una respuesta
    public void changeLeftRight(View v) {
        if (left) {
            left = false;
        } else {
            left = true;
        }

    }
}

/**class PhotoHandler implements Camera.PictureCallback {

    private final Context context;

    public PhotoHandler(Context context) {
        this.context = context;
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {

        File pictureFileDir = getDir();

        if (!pictureFileDir.exists() && !pictureFileDir.mkdirs()) {

            Log.d("test", "Can't create directory to save image.");
            Toast.makeText(context, "Can't create directory to save image.",
                    Toast.LENGTH_LONG).show();
            return;

        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
        String date = dateFormat.format(new Date());
        String photoFile = "Picture_" + date + ".jpg";

        String filename = pictureFileDir.getPath() + File.separator + photoFile;

        File pictureFile = new File(filename);

        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            fos.write(data);
            fos.close();
            Toast.makeText(context, "New Image saved:" + photoFile,
                    Toast.LENGTH_LONG).show();
        } catch (Exception error) {
            Log.d("test", "File" + filename + "not saved: "
                    + error.getMessage());
            Toast.makeText(context, "Image could not be saved.",
                    Toast.LENGTH_LONG).show();
        }
    }

    private File getDir() {
        File sdDir = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return new File(sdDir, "CameraAPIDemo");
    }
}*/