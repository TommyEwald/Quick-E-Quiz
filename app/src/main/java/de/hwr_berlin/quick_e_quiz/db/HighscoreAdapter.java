package de.hwr_berlin.quick_e_quiz.db;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import de.hwr_berlin.quick_e_quiz.R;

/**
 * Created by EwaldT on 21.03.2016.
 */
public class HighscoreAdapter extends ArrayAdapter<Highscore>{
    public HighscoreAdapter(Context context, ArrayList<Highscore> highscores){
        super(context, 0, highscores);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Highscore highscore = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_highscore_item, parent, false);
        }
        TextView tvRank = (TextView) convertView.findViewById(R.id.tvRank);
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvScore = (TextView) convertView.findViewById(R.id.tvScore);

        tvRank.setText(String.valueOf(position + 1));
        tvName.setText(highscore.getName());
        tvScore.setText(String.valueOf(highscore.getScore()));

        return convertView;
    }


}
