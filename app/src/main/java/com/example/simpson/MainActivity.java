package com.example.simpson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainAcitivityTAG_";
    public static final String CHARACTER_BUNDLE_KEY = "BUNDLE_KEY";

    private ListView mListView;

    private CustomAdapter arrayAdapter;

    List<SimCharacter> characters ;

    String jsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.lst_view);

        doNetwork();

    }

    private void loadDescription(SimCharacter aCharacter) {

        Intent intent = new Intent(this, DetailActivity.class);

        intent.putExtra(CHARACTER_BUNDLE_KEY, aCharacter);

        startActivity(intent);
    }

    public void doNetwork(){

        String Url = "https://api.duckduckgo.com/?q=simpsons+characters&format=json";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(Url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                  jsonString = response.body().string();
                  doJSON(jsonString );
            }
        });
    }

    public void doJSON(final String json){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                characters  = new ArrayList<SimCharacter>();
                try {
                    JSONObject  root = new JSONObject(json);
                    JSONArray  relatedTopics = root.getJSONArray("RelatedTopics");

                    for (int i = 0; i < relatedTopics.length() ; i++) {

                        JSONObject result = new JSONObject(relatedTopics.get(i).toString());

                        String txt = result.getString("Text");
                        int index  = txt.indexOf('-');

                        String desc = result.getString("Result");

                        characters.add( new SimCharacter(txt.substring(0, index), desc));
                    }

                    arrayAdapter = new CustomAdapter(getApplicationContext(), characters);
                    mListView.setAdapter(arrayAdapter);
                    arrayAdapter.notifyDataSetChanged();


                    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            SimCharacter aCharacter = arrayAdapter.getItem(i);
                            loadDescription(aCharacter);
                    }
        });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
