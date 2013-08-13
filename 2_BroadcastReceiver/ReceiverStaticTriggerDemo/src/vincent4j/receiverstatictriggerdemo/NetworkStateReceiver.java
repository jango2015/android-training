package vincent4j.receiverstatictriggerdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NetworkStateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent arg1) {
        Log.d("4J", "onReceive called.");
    }

}
