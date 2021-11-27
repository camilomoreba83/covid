package co.edu.unab.covid_app.http;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AbcUniversitySingleton {
    private static AbcUniversitySingleton instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    private AbcUniversitySingleton(Context context) {
        ctx = context;
        requestQueue =  getRequestQueue();
    }
    public static synchronized AbcUniversitySingleton getInstance(Context context) {
        if (instance == null) {
            instance = new AbcUniversitySingleton(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }
    public <T> void addQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
