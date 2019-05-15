package com.appmoviles.retomoviles;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.appmoviles.retomoviles.Service.ServiceManager;
import com.appmoviles.retomoviles.model.PlayList;
import com.appmoviles.retomoviles.util.AdapterPlayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements AdapterPlayList.OnItemClickListener {

    private EditText et_buscar_lista;
    private RecyclerView lista_plays;
    private ImageView iv_buscar;
    private Button btn_next;
    private Button btn_prev;
    private int numero_Lista;

    private SharedPreferences myPreferences;

    private AdapterPlayList adapterPlayList;
    private PlayList playList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        myPreferences
                = (SharedPreferences) PreferenceManager.getDefaultSharedPreferences(this);
        numero_Lista = myPreferences.getInt("NUMERO_LISTA", 0);


        et_buscar_lista = findViewById(R.id.et_buscar_lista);
        lista_plays = findViewById(R.id.lista_plays);
        iv_buscar = findViewById(R.id.iv_buscar);
        btn_next = findViewById(R.id.btn_next_play_list);
        btn_prev = findViewById(R.id.btn_prev_play_list);

        adapterPlayList = new AdapterPlayList();
        adapterPlayList.setListener(this);

        new Thread(() -> {
            new ServiceManager.PLAYLISTGET(response -> runOnUiThread(() -> {
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
                        String track_list = playListJson.getString("tracklist");
                        String id_play_list = playListJson.getString("id");

                        PlayList playList = new PlayList(nombre_lista, nombre_creador, numero_canciones, descripcion, "");
                        playList.setIcon_play_list(icono);
                        playList.setTrackList(track_list);
                        playList.setId_playList(id_play_list);
                        adapterPlayList.agregarPlayList(playList);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }));
        }).start();

        lista_plays.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        lista_plays.setLayoutManager(manager);
        lista_plays.setAdapter(adapterPlayList);


        iv_buscar.setOnClickListener(v -> {
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
                                String track_list = playListJson.getString("tracklist");
                                String id_play_list = playListJson.getString("id");

                                PlayList playList = new PlayList(nombre_lista, nombre_creador, numero_canciones, descripcion, "");
                                playList.setIcon_play_list(icono);
                                playList.setTrackList(track_list);
                                playList.setId_playList(id_play_list);
                                runOnUiThread(() -> adapterPlayList.agregarPlayList(playList));

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, buscador);
                }).start();

                lista_plays.setHasFixedSize(true);
                LinearLayoutManager manager1 = new LinearLayoutManager(v.getContext());
                lista_plays.setLayoutManager(manager1);
                lista_plays.setAdapter(adapterPlayList);
            }
        });


        et_buscar_lista.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode==KeyEvent.KEYCODE_ENTER) { //Whenever you got user click enter. Get text in edittext and check it equal test1. If it's true do your code in listenerevent of button3
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
                                    String track_list = playListJson.getString("tracklist");
                                    String id_play_list = playListJson.getString("id");

                                    PlayList playList = new PlayList(nombre_lista, nombre_creador, numero_canciones, descripcion, "");
                                    playList.setIcon_play_list(icono);
                                    playList.setTrackList(track_list);
                                    playList.setId_playList(id_play_list);
                                    runOnUiThread(() -> adapterPlayList.agregarPlayList(playList));

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }, buscador);
                    }).start();

                    lista_plays.setHasFixedSize(true);
                    LinearLayoutManager manager1 = new LinearLayoutManager(v.getContext());
                    lista_plays.setLayoutManager(manager1);
                    lista_plays.setAdapter(adapterPlayList);
                }
            }
            return true;
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        myPreferences.edit().clear().commit();
    }
}
