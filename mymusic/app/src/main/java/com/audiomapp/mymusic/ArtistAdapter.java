package com.audiomapp.mymusic;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by alvararias on 10/03/16.
 */

public class ArtistAdapter extends ArrayAdapter<ArtistDeezer>{
    
    public ArrayList<ArtistDeezer> artistList;

    public ArtistAdapter(Context context, ArrayList<ArtistDeezer> artistList){
        super(context, R.layout.list_view_cell, artistList);
        this.artistList = artistList;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listViewItem = inflater.inflate(R.layout.list_view_cell, parent, false);

        TextView textViewAmount = (TextView) listViewItem.findViewById(R.id.textViewSongName);
        //TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewArtistName);

        textViewAmount.setText(artistList.get(position).name);

        //textViewName.setText(artistList.get(position).nb_album);

        ImageView imageArtist = (ImageView) listViewItem.findViewById(R.id.imageViewArtist);

        // imagen
        String url_artist = artistList.get(position).picture_small;

        Picasso.with(getContext()).load(url_artist).into(imageArtist);


        if(position %2 == 0){
            if(Build.VERSION.SDK_INT < 23){
                listViewItem.setBackgroundColor(getContext().getResources().getColor(R.color.blanco));
            } else {
               // listViewItem.setBackgroundColor(getContext().getColor(R.color.naranja));
            }
        }

        return listViewItem;
    }
}
