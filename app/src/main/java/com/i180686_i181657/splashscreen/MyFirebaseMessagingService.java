package com.i180686_i181657.splashscreen;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService
{

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        getFirebaseMessage(remoteMessage.getNotification().getTitle() , remoteMessage.getNotification().getBody());

    }

    private void getFirebaseMessage(String title , String msg)
    {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this , "myFirebasechannel")
                .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                .setContentTitle(title)
                .setContentText(msg)
                .setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);

        managerCompat.notify(101 , builder.build());

    }
}
