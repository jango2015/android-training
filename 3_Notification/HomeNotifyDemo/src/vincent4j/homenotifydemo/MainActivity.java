package vincent4j.homenotifydemo;

import vincent4j.homenotifydemo.HomeWatcher.OnHomePressedListener;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends ActionBarActivity {
    
    private HomeWatcher mHomeWatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment()).commit();
        }
        
        mHomeWatcher = new HomeWatcher(this);
        
        mHomeWatcher.setOnHomePressedListener(new OnHomePressedListener() {
            
            @Override
            public void onHomePressed() {
                Log.d("4J", "Home pressed");
                showHomeNotification();
            }
            
            @Override
            public void onHomeLongPressed() {
                Log.d("4J", "Home pressed Long");
            }
        });
        
    }
    
    private void showHomeNotification() {
        Notification notification =
                new NotificationCompat.Builder(this)
//        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
                .setSmallIcon(R.drawable.ic_stat_notify_home_pressed)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setTicker("tickerText")
                .build();
        
        Intent notificationIntent = new Intent(this, getClass());
        
        PendingIntent contentIntent = PendingIntent.getActivity(
                this,
                0,
                notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        
        notification.contentIntent = contentIntent;
        // 按下之后从 Notification Draw 自动移除
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE); 
        manager.notify(0, notification);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        mHomeWatcher.startWatch();
    }
    
    @Override
    protected void onPause() {
        mHomeWatcher.stopWatch();
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container,
                    false);
            return rootView;
        }
    }

}
