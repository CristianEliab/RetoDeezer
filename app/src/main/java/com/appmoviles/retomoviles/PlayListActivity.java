package com.appmoviles.retomoviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.appmoviles.retomoviles.Service.ServiceManager;
import com.appmoviles.retomoviles.model.Cancion;
import com.appmoviles.retomoviles.model.PlayList;
import com.appmoviles.retomoviles.util.AdapterCanciones;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PlayListActivity extends AppCompatActivity implements AdapterCanciones.OnItemClickListener {

    private PlayList playList;
    private TextView tv_detalle_nombre_play_list;
    private TextView tv_detalle_descripcion;
    private TextView tv_detalle;
    private ImageView iv_imagen_play;
    private ImageView iv_icon_return;
    private AdapterCanciones adapterCanciones;
    private RecyclerView lista_canciones;

    private ArrayList<Cancion> listaCaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);

        Intent intent = this.getIntent();
        playList = (PlayList) intent.getSerializableExtra("PLAY_LIST");

        tv_detalle_nombre_play_list = findViewById(R.id.tv_detalle_nombre_play_list);
        tv_detalle_descripcion = findViewById(R.id.tv_detalle_descripcion);
        tv_detalle = findViewById(R.id.tv_detalle);
        iv_imagen_play = findViewById(R.id.iv_imagen_play);
        iv_icon_return = findViewById(R.id.iv_icon_return);
        lista_canciones = findViewById(R.id.lista_canciones);

        tv_detalle_nombre_play_list.setText(playList.getNombrePlayList());
        tv_detalle_descripcion.setText(playList.getDescripcion());
        tv_detalle.setText(playList.getNombreCreador());

        Glide.with(this).load(playList.getIcon_play_list()).into(iv_imagen_play);

        adapterCanciones = new AdapterCanciones();
        adapterCanciones.setListener(this);

        iv_icon_return.setOnClickListener(v -> onBackPressed());

        new Thread(() -> {
            new ServiceManager.CANCIONESGET(response -> {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject cancionJSON = jsonArray.getJSONObject(i);
                        String nombre_cancion = cancionJSON.getString("title");
                        JSONObject artistJson = cancionJSON.getJSONObject("artist");
                        String nombre_artista = artistJson.getString("name");
                        String anio = cancionJSON.getString("rank");
                        String icono_cancion = artistJson.getString("picture");
                        String descripcion = cancionJSON.getString("duration");
                        String n_fans = cancionJSON.getString("explicit_lyrics");
                        String cancion_link = cancionJSON.getString("preview");

                        Cancion cancion = new Cancion(nombre_cancion, nombre_artista, anio, descripcion, n_fans);
                        cancion.setIcon(icono_cancion);
                        cancion.setCancion(cancion_link);

                        runOnUiThread(() -> adapterCanciones.agregarCancion(cancion));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, playList.getTrackList());
        }).start();

        lista_canciones.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        lista_canciones.setLayoutManager(manager);
        lista_canciones.setAdapter(adapterCanciones);


        iv_icon_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onItemClick(Cancion cancion) {
        if (cancion != null){
            Intent i = new Intent(PlayListActivity.this, DetalleActivity.class);
            i.putExtra("CANCION", cancion);
            startActivity(i);
        }
    }

}
