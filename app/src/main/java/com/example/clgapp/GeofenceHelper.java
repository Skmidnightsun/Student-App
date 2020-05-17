package com.example.clgapp;

import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.maps.model.LatLng;

public class GeofenceHelper extends ContextWrapper {
    private static  final String TAG="GeofenceHelper";
    PendingIntent pendingIntent;
    public GeofenceHelper(Context base) {
        super(base);
    }


    public GeofencingRequest getGeofencingRequest(Geofence geofence)
    {
        return new GeofencingRequest.Builder()
                .addGeofence(geofence)
                .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
                .build();
    }


    public Geofence getGeofence(String ID, LatLng latLng,float radius,int transitionTypes)
    {
        return new Geofence.Builder().setCircularRegion(latLng.latitude,latLng.longitude,radius)
                .setRequestId(ID)
                .setTransitionTypes(transitionTypes)
                .setLoiteringDelay(5000)
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .build();
    }


    public PendingIntent getPendingIntent()
    {
        if(pendingIntent!=null) {
            return pendingIntent;
        }
        Intent intent=new Intent(this, GeofenceBroadcastReciever.class);
                pendingIntent=PendingIntent.getBroadcast(this,2607,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        return pendingIntent;
    }
    public String getErrorString(Exception e)
    {
        if(e instanceof ApiException)
        {
            ApiException apiException=(ApiException)e;
            switch(apiException.getStatusCode())
            {
                case GeofenceStatusCodes
                      .GEOFENCE_NOT_AVAILABLE:
                    return "Geofence Not Available ";
                case GeofenceStatusCodes
                        .GEOFENCE_TOO_MANY_GEOFENCES:
                    return "Geofence too many geofences ";
                case GeofenceStatusCodes
                        .GEOFENCE_TOO_MANY_PENDING_INTENTS:
                    return "Geofence too many pending intents ";
            }
        }
        return e.getLocalizedMessage();
    }


}
