package com.appmoviles.retomoviles;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.appmoviles.retomoviles.model.Cancion;
import com.appmoviles.retomoviles.model.PlayList;
import com.bumptech.glide.Glide;

public class DetalleActivity extends AppCompatActivity {

    private Cancion cancion;
    private ImageView iv_imagen_play;
    private TextView tv_detalle_nombre_cancion;
    private TextView tv_detalle_nombre_artista;
    private TextView tv_detalle_descripcion_albun_cancion;
    private TextView tv_detalle;
    private Button btn_escuchar_cancion;
    private ImageView iv_icon_return;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Intent intent = this.getIntent();
        cancion = (Cancion) intent.getSerializableExtra("CANCION");

        iv_imagen_play = findViewById(R.id.iv_imagen_play_cancion);
        tv_detalle_nombre_cancion = findViewById(R.id.tv_detalle_nombre_cancion);
        tv_detalle_nombre_artista = findViewById(R.id.tv_detalle_nombre_artista);
        tv_detalle_descripcion_albun_cancion = findViewById(R.id.tv_detalle_descripcion_albun_cancion);
        tv_detalle = findViewById(R.id.tv_detalle);
        btn_escuchar_cancion = findViewById(R.id.btn_escuchar_cansion);
        iv_icon_return = findViewById(R.id.iv_icon_return);

        Glide.with(this).load(cancion.getIcon()).into(iv_imagen_play);
        tv_detalle_nombre_cancion.setText(cancion.getNombreCancion());
        tv_detalle_nombre_artista.setText(cancion.getNombreArtista());
        tv_detalle_descripcion_albun_cancion.setText(cancion.getAnioLanzamiento());
        tv_detalle.setText(cancion.getDescripcion());

        iv_icon_return.setOnClickListener(v -> onBackPressed());


        btn_escuchar_cancion.setOnClickListener(v -> {
            try {
                Uri uri = Uri.parse(cancion.getCancion());
                MediaPlayer player = new MediaPlayer();
                player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                player.setDataSource(DetalleActivity.this, uri);
                player.prepare();
                player.start();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        });
    }
}
