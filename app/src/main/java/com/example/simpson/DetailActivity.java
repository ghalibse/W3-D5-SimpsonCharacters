package com.example.simpson;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivityTAG_";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textView = (TextView) findViewById(R.id.detail_txt);

        SimCharacter aCharacter = getIntent()
                .getExtras()
                .getParcelable(MainActivity.CHARACTER_BUNDLE_KEY);
        textView.setText(aCharacter.description);
    }

}
