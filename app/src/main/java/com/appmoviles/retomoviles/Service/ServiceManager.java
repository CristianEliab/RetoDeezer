package com.appmoviles.retomoviles.Service;

import com.appmoviles.retomoviles.util.HTTPSWebUtilDomi;

import java.io.IOException;

public class ServiceManager {

    public static final String PLAY_LIST = "https://api.deezer.com/user/2529/playlists";
    public static final String SEARCH = "https://api.deezer.com/search/playlist?q=";

    public static class PLAYLISTGET {
        OnResponseListener listener;

        public PLAYLISTGET(OnResponseListener listener) {
            this.listener = listener;
            HTTPSWebUtilDomi util = new HTTPSWebUtilDomi();
            try {
                String response = util.GETrequest(PLAY_LIST);
                listener.onResponse(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public interface OnResponseListener {
            void onResponse(String response);
        }
    }

    public static class CANCIONESGET {
        OnResponseListener listener;

        public CANCIONESGET(OnResponseListener listener, String listaCansiones) {
            this.listener = listener;
            HTTPSWebUtilDomi util = new HTTPSWebUtilDomi();
            try {
                String response = util.GETrequest(listaCansiones);
                listener.onResponse(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        public interface OnResponseListener {
            void onResponse(String response);
        }
    }

    public static class SEARCHGET {
        OnResponseListener listener;

        public SEARCHGET(OnResponseListener listener, String buscador) {
            this.listener = listener;
            HTTPSWebUtilDomi util = new HTTPSWebUtilDomi();
            try {
                String response = util.GETrequest(SEARCH+buscador);
                listener.onResponse(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        public interface OnResponseListener {
            void onResponse(String response);
        }
    }

}
