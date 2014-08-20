package vincent4j.homenotifydemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public class HomeWatcher {

    private static final String TAG = "HomeWatcher";

    private Context mContext;
    private IntentFilter mFilter;
    private OnHomePressedListener mListener;
    private HomeReceiver mRecevier;

    public HomeWatcher(Context context) {
        mContext = context;
        mFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
    }

    public void setOnHomePressedListener(OnHomePressedListener listener) {
        mListener = listener;
        mRecevier = new HomeReceiver();
    }

    public void startWatch() {
        if (mRecevier != null) {
            mContext.registerReceiver(mRecevier, mFilter);
        } else {
            Log.e(TAG, "Receiver is not initializd.");
        }
    }

    public void stopWatch() {
        if (mRecevier != null) {
            mContext.unregisterReceiver(mRecevier);
        }
    }

    public class HomeReceiver extends BroadcastReceiver {

        private final String SYSTEM_DIALOG_REASON = "reason";
        private final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";
        private final String SYSTEM_DIALOG_REASON_HOME = "homekey";

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            
            System.out.println("intent " + intent.getAction() + " " + intent.toString() + " " + intent.getDataString());

            if (Intent.ACTION_CLOSE_SYSTEM_DIALOGS.equals(action)) {
                String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON);

                if (reason != null) {
                    if (mListener != null) {
                        if (reason.equals(SYSTEM_DIALOG_REASON_HOME)) {
                            // 短按home键
                            Log.d(TAG, "Home key is pressed, and activity named " 
                                            + mContext.getClass().getSimpleName() 
                                            + " is on foreground.");
                            
                            mListener.onHomePressed();
                        } else if (reason
                                .equals(SYSTEM_DIALOG_REASON_RECENT_APPS)) {
                            // 长按home键
                            Log.d(TAG, "Home key is pressed long, and activity named " 
                                    + mContext.getClass().getSimpleName() 
                                    + " is on foreground.");
                            
                            mListener.onHomeLongPressed();
                        }
                    }
                }
            }
        }

    }
    
    /**
     * 按下 Home 键时的监听事件
     */
    public interface OnHomePressedListener {

        /**
         * 短按 Home 键的处理逻辑
         */
        public void onHomePressed();

        /**
         * 长按 Home 键的处理逻辑
         */
        public void onHomeLongPressed();

    }
    
}
