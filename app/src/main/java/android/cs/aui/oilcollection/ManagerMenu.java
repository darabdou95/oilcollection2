package android.cs.aui.oilcollection;

import android.content.Intent;
import android.cs.aui.oilcollection.classes.notificationService;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class ManagerMenu extends AppCompatActivity {
CardView shop,stats,help,pending,collector,display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startService(new Intent(this, notificationService.class));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shop= findViewById(R.id.locId);
        stats=findViewById(R.id.stats);
        help=findViewById(R.id.helpC);
        collector=findViewById(R.id.collector);
        display=findViewById(R.id.dispcollector);

        pending=findViewById(R.id.pending);

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerMenu.this,AddLocation.class);
                startActivity(intent);
            }
        });

        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerMenu.this,statActivity.class);
                startActivity(intent);
            }
        });
        pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerMenu.this,Collector.class);
                startActivity(intent);
            }
        });
        collector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerMenu.this,collectorActivity.class);
                startActivity(intent);
            }
        });
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerMenu.this,displaycollector.class);
                startActivity(intent);
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerMenu.this,help.class);
                startActivity(intent);
            }
        });







    }
}
