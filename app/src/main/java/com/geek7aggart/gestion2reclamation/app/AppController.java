
package com.geek7aggart.gestion2reclamation.app;

import android.app.Application;

/**
 * Created by BARA' on 12/05/2016.
 */
public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();

    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }


}

