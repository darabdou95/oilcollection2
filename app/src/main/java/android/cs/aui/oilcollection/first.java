package android.cs.aui.oilcollection;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class first extends AppCompatActivity {
    private Boolean isDissmissed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_first);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isDissmissed) {
                    startActivity(new Intent(first.this, MainActivity.class));
                }
            }
        }, 1500);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isDissmissed = true;
    }

}
