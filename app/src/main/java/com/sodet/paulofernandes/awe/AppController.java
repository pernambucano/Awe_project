package com.sodet.paulofernandes.awe;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;


public class AppController extends Application
{

    public static final String TAG = AppController.class.getSimpleName();
    private RequestQueue myRequestQueue;
    private ImageLoader myImageLoader;
    private static AppController myInstance;

    @Override
    public void onCreate()
    {
        super.onCreate();
        myInstance = this;
    }

    public static synchronized AppController getInstance()
    {
        return myInstance;
    }

    public RequestQueue getRequestQueue()
    {
        if (myRequestQueue == null)
        {
            myRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return myRequestQueue;
    }

    public ImageLoader getImageLoader()
    {
        getRequestQueue();

        if (myImageLoader == null)
        {
            myImageLoader = new ImageLoader(this.myRequestQueue, new LruBitmapCache());
        }

        return this.myImageLoader;
    }

    public <T> void addToRequestQueue (Request<T> req, String tag)
    {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req)
    {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag)
    {
        if (myRequestQueue != null)
        {
            myRequestQueue.cancelAll(tag);
        }
    }
}
