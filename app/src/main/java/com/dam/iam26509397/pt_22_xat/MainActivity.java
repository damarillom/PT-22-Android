package com.dam.iam26509397.pt_22_xat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean left = true;

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
}
