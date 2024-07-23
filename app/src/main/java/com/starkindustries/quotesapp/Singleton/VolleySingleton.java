package com.starkindustries.quotesapp.Singleton;
import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
public class VolleySingleton {
    public static VolleySingleton INSTANCE;
    public RequestQueue requestQueue;
    public Context context;
    public static synchronized VolleySingleton getInstance(Context context)
    {
        if(INSTANCE==null)
            INSTANCE=new VolleySingleton(context.getApplicationContext());
        return INSTANCE;
    }
    public RequestQueue getRequestQueue(Context context) {
        if(requestQueue==null)
            requestQueue= Volley.newRequestQueue(context.getApplicationContext());
        return requestQueue;
    }
    public <T> void addRequest(Request<T> request)
    {
        if(requestQueue==null)
            getInstance(context.getApplicationContext());
        requestQueue.add(request);
    }
    public VolleySingleton(Context context)
    {
        this.context=context;
        this.requestQueue=getRequestQueue(context.getApplicationContext());
    }
}
