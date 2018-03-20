package com.kantapp.fullbattary;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textview;
    Button button;
    IntentFilter intentfilter;
    int deviceStatus;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.button1);
        textview=findViewById(R.id.textView1);

        intentfilter=new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        MainActivity.this.registerReceiver(broadcastreceiver,intentfilter);

        mediaPlayer=MediaPlayer.create(MainActivity.this,R.raw.game_complete);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                button.setVisibility(View.GONE);
            }
        });

    }

    private BroadcastReceiver broadcastreceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            deviceStatus=intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);


            if (deviceStatus == BatteryManager.BATTERY_STATUS_FULL){

                textview.setText("Battery Status = Battery Full ");
                button.setVisibility(View.VISIBLE);
                mediaPlayer=MediaPlayer.create(MainActivity.this,R.raw.game_complete);
                mediaPlayer.start();
                mediaPlayer.setLooping(true);

            }
            if(deviceStatus==BatteryManager.BATTERY_STATUS_CHARGING)
            {
                Toast.makeText(context, "Mobile is Charging", Toast.LENGTH_SHORT).show();
            }


        }
    };


}
