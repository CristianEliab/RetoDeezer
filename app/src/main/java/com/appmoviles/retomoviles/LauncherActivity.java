package com.appmoviles.retomoviles;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class LauncherActivity extends AppCompatActivity {

    private AnimationDrawable animacion;
    private ImageView iv_splash_screen;
    private Animation transicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        iv_splash_screen = findViewById(R.id.iv_splash_screen);
        iv_splash_screen.setBackgroundResource(R.drawable.animacion_item);
        animacion = (AnimationDrawable) iv_splash_screen.getBackground();
        goToMain();
    }

    private void goToMain() {
        transicion = AnimationUtils.loadAnimation(this, R.anim.animacion);
        iv_splash_screen.startAnimation(transicion);
        transicion.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                siguienteActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void siguienteActivity() {
        animacion.stop(); //Paramos el AnimationDrawable
        Intent intento = new Intent(this, MainActivity.class); // Lanzamos SiguienteActivity
        startActivity(intento);
        finish(); //Finalizamos este activity
    }
}
