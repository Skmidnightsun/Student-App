package com.example.clgapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;
import com.example.notifications.NotificationHelper;

import java.util.List;

public class GeofenceBroadcastReciever extends BroadcastReceiver {
    private static final String TAG = "GeofenceBroadcastReciev";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        Toast.makeText(context, "Geofence Triggered...",Toast.LENGTH_SHORT).show();
        NotificationHelper notificationHelper=new NotificationHelper(context);

        GeofencingEvent geofencingEvent= GeofencingEvent.fromIntent(intent);
        if(geofencingEvent.hasError()){
            Log.d(TAG, "onRecieve: Error recieving geofence event...");
            return;
        }

        List<Geofence> geofence=geofencingEvent.getTriggeringGeofences();
        /*for(Geofence geofence: geofenceList) {
            Log.d(TAG, "onReceive: "+geofence.getRequestID());
        }*/
        Location location=geofencingEvent.getTriggeringLocation();
        int transitionType=geofencingEvent.getGeofenceTransition();
        switch(transitionType)
        {
            case Geofence.GEOFENCE_TRANSITION_ENTER:
                Toast.makeText(context, "Geofence Enter", Toast.LENGTH_SHORT).show();
                notificationHelper.sendHighPriorityNotification("Geofence Enter","",MapsActivity.class);
                break;
            case Geofence.GEOFENCE_TRANSITION_EXIT:
                Toast.makeText(context, "Geofence Exit", Toast.LENGTH_SHORT).show();
                notificationHelper.sendHighPriorityNotification("Geofence Exit","",MapsActivity.class);
                break;
            case Geofence.GEOFENCE_TRANSITION_DWELL:
                Toast.makeText(context, "Geofence Loitering", Toast.LENGTH_SHORT).show();
                notificationHelper.sendHighPriorityNotification("Geofence Loitering","",MapsActivity.class);
                break;
        }
    }
}
