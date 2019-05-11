package com.appmoviles.retomoviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.appmoviles.retomoviles.Service.ServiceManager;
import com.appmoviles.retomoviles.model.Cancion;
import com.appmoviles.retomoviles.model.PlayList;
import com.appmoviles.retomoviles.util.AdapterPlayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements AdapterPlayList.OnItemClickListener {

    private EditText et_buscar_lista;
    private RecyclerView lista_plays;
    private ImageView iv_buscar;

    private AdapterPlayList adapterPlayList;
    private PlayList playList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        et_buscar_lista = findViewById(R.id.et_buscar_lista);
        lista_plays = findViewById(R.id.lista_plays);
        iv_buscar = findViewById(R.id.iv_buscar);

        adapterPlayList = new AdapterPlayList();
        adapterPlayList.setListener(this);

        new Thread(() -> {
            new ServiceManager.PLAYLISTGET(new ServiceManager.PLAYLISTGET.OnResponseListener() {
                @Override
                public void onResponse(final String response) {
                    runOnUiThread(() -> {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject playListJson = jsonArray.getJSONObject(i);
                                String nombre_lista = playListJson.getString("title");
                                JSONObject creadorJson = playListJson.getJSONObject("creator");
                                String nombre_creador = creadorJson.getString("name");
                                String numero_canciones = playListJson.getString("nb_tracks");
                                String icono = playListJson.getString("picture_big");
                                String descripcion = playListJson.getString("creation_date");
                                String n_fans = playListJson.getString("fans");
                                String track_list = playListJson.getString("tracklist");

                                PlayList playList = new PlayList(nombre_lista, nombre_creador, numero_canciones, descripcion, n_fans);
                                playList.setIcon_play_list(icono);
                                playList.setTrackList(track_list);
                                adapterPlayList.agregarPlayList(playList);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    });
                }
            });
        }).start();

        lista_plays.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        lista_plays.setLayoutManager(manager);
        lista_plays.setAdapter(adapterPlayList);


        iv_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buscador = et_buscar_lista.getText().toString().trim();
                if (!buscador.equals("")) {
                    adapterPlayList = new AdapterPlayList();
                    adapterPlayList.setListener((AdapterPlayList.OnItemClickListener) v.getContext());
                    new Thread(() -> {
                        new ServiceManager.SEARCHGET(response -> {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject playListJson = jsonArray.getJSONObject(i);
                                    String nombre_lista = playListJson.getString("title");
                                    JSONObject creadorJson = playListJson.getJSONObject("user");
                                    String nombre_creador = creadorJson.getString("name");
                                    String numero_canciones = playListJson.getString("nb_tracks");
                                    String icono = playListJson.getString("picture_big");
                                    String descripcion = playListJson.getString("creation_date");
                                    String n_fans = playListJson.getString("type");
                                    String track_list = playListJson.getString("tracklist");

                                    PlayList playList = new PlayList(nombre_lista, nombre_creador, numero_canciones, descripcion, n_fans);
                                    playList.setIcon_play_list(icono);
                                    playList.setTrackList(track_list);
                                    runOnUiThread(() -> adapterPlayList.agregarPlayList(playList));

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }, buscador);
                    }).start();

                    lista_plays.setHasFixedSize(true);
                    LinearLayoutManager manager = new LinearLayoutManager(v.getContext());
                    lista_plays.setLayoutManager(manager);
                    lista_plays.setAdapter(adapterPlayList);
                }
            }
        });

    }

    @Override
    public void onItemClick(PlayList playList) {
        if (playList != null) {
            Intent i = new Intent(MainActivity.this, PlayListActivity.class);
            i.putExtra("PLAY_LIST", playList);
            startActivity(i);
        }
    }
}
