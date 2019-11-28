package com.audiomapp.mymusic;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

public class main_my_music extends AppCompatActivity {

// Define variables actividad

    public static final String SHOW_MUSIC = "artistamusic";

// var main activity
    public ArrayList<ArtistDeezer> artistList;
    public ArtistAdapter adapter;
    public Button miButton;
    public Button againButton;
    public EditText miEdit;
    public static String nameSelected;
    public static String linkImgSelected;

    //1-busqueda artista:
    public String url1 = "http://api.deezer.com/search/artist?q=";
    // busqueda album
    public String url2 = "http://api.deezer.com/search/album?q=";

    //Busqueda de tracks de un album
    // "https://api.deezer.com/album/107859/tracks

    //3-search (temas) Artista
    public String url4 = "http://api.deezer.com/search?q=";

    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_my_music);

        artistList = new ArrayList<>();

        // conf Adapter
        adapter = new ArtistAdapter(this, artistList);

        ListView ArtistListView = (ListView) findViewById(R.id.listViewArtist);

        ArtistListView.setAdapter(adapter);

        // toca elemto lista
        ArtistListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Llama el método edit cuando toca un elemento de la lista.

                nameSelected = artistList.get(position).name;
                linkImgSelected = artistList.get(position).picture_medium;

                Log.d("Artist Selected", nameSelected);
                Log.d("Link Selected", linkImgSelected);

                showArtist2();

            }
        });

        // oculta el teclado hasta que el usuario presiona edit
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );

        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        miButton = (Button) findViewById(R.id.buttonSearch);
        miEdit = (EditText) findViewById(R.id.editText);

        // cierra el teclado virtual
        miButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        InputMethodManager inputManager = (InputMethodManager)
                                getSystemService(Context.INPUT_METHOD_SERVICE);

                        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);

                        //Dispara el boton
                        Log.v("Artist", miEdit.getText().toString());

                        adapter.clear();
                        getArtist();
                        //alertMsj();
                        spinner.setVisibility(View.VISIBLE);
                    }
                });

    }

    public void getArtist() {

        String url = (url1 + miEdit.getText().toString());
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

                            Type listType = new TypeToken<ArrayList<ArtistDeezer>>() {
                            }.getType();
                            
                            // deserialización
                            artistList = gson.fromJson(jsonArray.toString(), listType);

                            // lleva al las celdas 
                            adapter.addAll(artistList);

                            // notifica al adapter
                            adapter.notifyDataSetChanged();

                            Log.d("Artist", jsonArray.toString());


                        } catch (Exception e) {
                            e.printStackTrace();

                            // Info si no resulta la consulta
                            alertMsj();
                        }
                    }
                });


            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();

                // Info al usuario que salio mal la consulta
                alertMsj();
            }
        };

        client.newCall(request).enqueue(callback);

    }


    private void showArtist2(){

        Intent i = new Intent(main_my_music.this, ShowArtistActivity.class);
        String strName = nameSelected;
        String strLink = linkImgSelected;
        i.putExtra("name", strName);
        i.putExtra("link", strLink);
        Log.d("Artist Enviado", strName);
        Log.d("Link Enviado", strLink);
        startActivity(i);
        spinner.setVisibility(View.VISIBLE);

    }

    private void alertMsj(){

        AlertDialog alertDialog = new AlertDialog.Builder(
                main_my_music.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Alert");

        // Setting Dialog Message
        alertDialog.setMessage("Load problem");

        // Setting Icon to Dialog
        //alertDialog.setIcon(R.drawable.tick);


        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // code to execute after dialog closed

                Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
            }
        });

        // Showing Alert Message
        alertDialog.show();

    }

}


