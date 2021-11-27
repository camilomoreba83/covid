package co.edu.unab.covid_app.http;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MarcadorSingleton {
    private static MarcadorSingleton instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    private MarcadorSingleton(Context context) {
        ctx = context;
        this.requestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue() {
        if (this.requestQueue==null){
            this.requestQueue = Volley.newRequestQueue(ctx);
        }
        return this.requestQueue;
    }


    public static synchronized MarcadorSingleton getInstance(Context context){
        if (instance==null){
            instance = new MarcadorSingleton(context);
        }
        return instance;
    }

    public <T> void addQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
