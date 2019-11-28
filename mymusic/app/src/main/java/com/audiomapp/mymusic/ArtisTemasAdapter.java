package com.audiomapp.mymusic;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alvararias on 13/03/16.
 */
public class ArtisTemasAdapter extends ArrayAdapter<ArtistTemasDeezer>{

        public ArrayList<ArtistTemasDeezer> artistListTem;

        public ArtisTemasAdapter(Context context, ArrayList<ArtistTemasDeezer> artistListTem){
            super(context, R.layout.list_view_cell_temas, artistListTem);
            this.artistListTem = artistListTem;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View listViewItem = inflater.inflate(R.layout.list_view_cell_temas, parent, false);

            TextView textView1 = (TextView) listViewItem.findViewById(R.id.textViewSongName);
            //TextView textView2 = (TextView) listViewItem.findViewById(R.id.textViewSongName2);

            textView1.setText(artistListTem.get(position).title);

            //textView2.setText(artistListTem.get(position).title_short);

            //ImageView imagen = (ImageView) listViewItem.findViewById(R.id.imagePlaySongArtist);

            //imagen.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_play_circle_fill_24dp));

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



