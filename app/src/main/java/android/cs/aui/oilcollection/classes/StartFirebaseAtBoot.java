package android.cs.aui.oilcollection.classes;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class StartFirebaseAtBoot extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(notificationService.class.getName()));
    }
}