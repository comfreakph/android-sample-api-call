package com.example.twitter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.twitter.adapters.TweetsAdapter;
import com.example.twitter.models.Tweets;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private TweetsAdapter adapter;
    private List<Tweets> tweetsList;

    private EditText editTextScreenName;
    private Button buttonOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextScreenName = (EditText) findViewById(R.id.editTextScreenName);
        buttonOk = (Button) findViewById(R.id.buttonOk);

        recyclerView = findViewById(R.id.recyclerViewBranch);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.buttonOk).setOnClickListener(this);

        loadTweets();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonOk:
                loadTweets();
                break;
        }
    }

    private void loadTweets() {
        String screenname = editTextScreenName.getText().toString();

        OkHttpClient client = new OkHttpClient();

        String url = "https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=" + screenname;

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", "Bearer AAAAAAAAAAAAAAAAAAAAAEDJCAEAAAAAaWvMGEjge1OGqg6oNvwL%2F3M%2BomM%3DTVJQPhamXtv4RgMBpynJsJJYOD2Heh19Dbo4wjabnc0poIjE5d")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();

                    Gson gson = new Gson();

                    final List<Tweets> tweets = gson.fromJson(myResponse, new TypeToken<List<Tweets>>(){}.getType());

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter = new TweetsAdapter(MainActivity.this, tweets);
                            recyclerView.setAdapter(adapter);

                            tweetsList = tweets;
                        }
                    });
                }
            }
        });
    }
}
