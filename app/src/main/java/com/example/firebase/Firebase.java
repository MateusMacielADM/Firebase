package com.example.firebase;


public class Firebase extends android.app.Application {

    @Override
    public void onCreate(){
        super.onCreate();

        com.firebase.client.Firebase.setAndroidContext(this);

        com.firebase.client.Firebase.getDefaultConfig().setPersistenceEnabled(true);

    }
}
