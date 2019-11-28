package com.audiomapp.mymusic;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShowArtistActivity extends AppCompatActivity {

    private ArtistTemasDeezer artist;
    ImageView image;
    public String namSel;
    public String linkSel;

    // var main activity
    public ArrayList<ArtistTemasDeezer> artistListTem;
    public ArtisTemasAdapter adapter;
    public static String nameTemaSelected;
    public static String linkTemaSelected;

    //3-search (temas) Artista
    public String url4 = "http://api.deezer.com/search?q=";

    public int progressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_artist);

        // dec barra de avance
        //ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar2);

       Bundle b = getIntent().getExtras();
        namSel = b.getString("name");
        linkSel = b.getString("link");

        if (namSel != null) {

            TextView textArtist = (TextView) findViewById(R.id.textViewArtistClick);
            textArtist.setText(namSel);

            image = (ImageView) findViewById(R.id.imageViewArstisSelected);

            Picasso.with(getBaseContext()).load(linkSel).into(image);

        }


        Button button = (Button) findViewById(R.id.volverMain);

        button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                //endPlayer();
                finish();

            }

        });

        // llama al ListView
        artistListTem = new ArrayList<>();

        adapter = new ArtisTemasAdapter(this, artistListTem);

        ListView ArtistListView2 = (ListView) findViewById(R.id.listViewArtistTemas);

        ArtistListView2.setAdapter(adapter);

        // busca el listado de temas
        getArtistTemas();

        // toca elemto lista
        ArtistListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Llama el método edit cuando toca un elemento de la lista.

                nameTemaSelected = artistListTem.get(position).title;
                linkTemaSelected = artistListTem.get(position).preview;

                Log.d("Artist Selected", nameTemaSelected);
                Log.d("Link Selected", linkTemaSelected);

                // hace cero el avance barra
                progressStatus = 0;

                // toca el tema
                playPreview();
                //TextView textView2 = (TextView) listViewItem.findViewById(R.id.textViewSongName2);
                //textView2.setText(artistListTem.get(position).title_short);)

                ImageView imagen = (ImageView) findViewById(R.id.imagePlaySongArtist);


                //imagen.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_play_circle_fill_24dp));


            }

        });


    }

    public void getArtistTemas() {

        String url = (url4 + namSel);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(url).build();

        Callback callback = new Callback() {

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String jsonString = response.body().string();

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(jsonString);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            Gson gson = new Gson();

                            Type listType = new TypeToken<ArrayList<ArtistTemasDeezer>>() {
                            }.getType();

                            // deserialización
                            artistListTem = gson.fromJson(jsonArray.toString(), listType);

                            // lleva al las celdas
                            adapter.addAll(artistListTem);

                            // notifica al adapter
                            adapter.notifyDataSetChanged();

                            Log.d("Temas", jsonArray.toString());

                        } catch (Exception e) {
                            e.printStackTrace();

                            // Info si no resulta la consulta
                        }
                    }
                });


            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();

                // Info al usuario que salio mal la consulta
            }
        };

        client.newCall(request).enqueue(callback);

    }


    public void playPreview() {

        final ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar2);

        MediaPlayer mediaPlayer = new MediaPlayer();

        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            public boolean onError(MediaPlayer mp, int what, int extra) {
                mp.reset();
                return false;
            }
        });

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {

                mp.start();

                // barra avance
                //progressStatus = mediaPlayer.getCurrentPosition();
                while(progressStatus < mp.getDuration()){

                    progressStatus +=1;

                    pb.setProgress(progressStatus);

                }



            }
        });

        try {
            mediaPlayer.setDataSource(linkTemaSelected);
            mediaPlayer.prepareAsync();





        } catch (IllegalArgumentException e) {

        } catch (IllegalStateException e) {

        } catch (IOException e) {

        }

    }


}
