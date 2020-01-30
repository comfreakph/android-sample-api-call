package com.example.twitter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twitter.R;
import com.example.twitter.models.Tweets;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.TweetsViewHolder> {
    private Context mCtx;
    private List<Tweets> tweetsList;

    public TweetsAdapter(Context mCtx, List<Tweets> tweetsList) {
        this.mCtx = mCtx;
        this.tweetsList = tweetsList;
    }

    @NonNull
    @Override
    public TweetsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_tweets_list, viewGroup, false);
        return new TweetsAdapter.TweetsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TweetsViewHolder TweetsViewHolder, int i) {
        final Tweets tweets = tweetsList.get(i);

        TweetsViewHolder.textViewName.setText(tweets.getText());
    }

    @Override
    public int getItemCount() {
        return tweetsList.size();
    }

    class TweetsViewHolder extends RecyclerView.ViewHolder{
        TextView textViewName;
        //RelativeLayout container;

        public TweetsViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            //container = itemView.findViewById(R.id.container);
        }
    }
}
