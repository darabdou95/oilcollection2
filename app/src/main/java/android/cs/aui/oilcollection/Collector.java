package android.cs.aui.oilcollection;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.cs.aui.oilcollection.classes.Shop;
import android.cs.aui.oilcollection.classes.Utiles;
import android.cs.aui.oilcollection.classes.collection;
import android.cs.aui.oilcollection.classes.collectorAdapter;
import android.cs.aui.oilcollection.classes.notificationService;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class Collector extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {
    //private DatabaseReference  databaseUser;
    private ArrayList<Shop> users;
    private ListView myListView;
    private collectorAdapter adapter;
    private int selected = -2;
    private GoogleMap googleMap;
    private MapFragment mapFragment;
    private double longitude,latitude;
    private boolean clicked;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startService(new Intent(this, notificationService.class));
        Button collectBtn;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collector);
        users = new ArrayList<Shop>();
        clicked = false;
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
       mapFragment.getMapAsync(this);
        myListView =  findViewById(R.id.listList);
        collectBtn = findViewById(R.id.colectorBtn);
        createListView();



        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                clicked = true;
                googleMap.clear();
                for(int a = 0; a < parent.getChildCount(); a++)
                {
                    ((CardView)parent.getChildAt(a).findViewById(R.id.collections)).setCardBackgroundColor(Color.WHITE);

                    longitude = users.get(position).getLongitude();
                    latitude = users.get(position).getLatitude();
                    LatLng placeLocation = new LatLng(latitude, longitude); //Make them global
                    Marker placeMarker = googleMap.addMarker(new MarkerOptions().position(placeLocation)
                            .title(""));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(placeMarker.getPosition(),14));
                    googleMap.animateCamera(CameraUpdateFactory.zoomTo(17), 1200, null);

                    ///////map location change////////
                    //////////////
                }
                ((CardView) view.findViewById(R.id.collections)).setCardBackgroundColor(Color.parseColor("#BDBDBD"));
                selected =position;
        }

        });


        collectBtn.setOnClickListener(this);

    }



    public void createListView (){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query = reference.child("shop").orderByChild("pending").equalTo(true);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                users.clear();
                Shop us = null;
                if (dataSnapshot.exists()) {

                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        us = issue.getValue(Shop.class);
                        users.add(us);
                    }


                } else {
                    Log.e("myError", "no pendings");
                    Toast.makeText(Collector.this, "No pendings", Toast.LENGTH_SHORT).show();
                    Collector.this.finish();
                }
                /////////use thus user here/////////////
                ///// us.getLatitude
                /////////////////////



                adapter = new collectorAdapter(Collector.this,users);
                myListView.setAdapter(adapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }

    @Override
    public void onClick(View v) {
        if (!clicked){
            Utiles.dialogWithOneButton(this, "Please select a collection");
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to collect it")
                .setCancelable(false)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Sure", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int x) {
                        Shop us = users.get(selected);
                        Calendar cal = Calendar.getInstance();
                        DatabaseReference databaseUser;
                        databaseUser = FirebaseDatabase.getInstance().getReference("shop");
                        us.setPending(false);
                        googleMap.clear();
                        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 1000, null);

                        databaseUser.child(us.getId()).setValue(us);

                        collection c = new collection(cal,us.getId(),us.getShopname());
                        databaseUser = FirebaseDatabase.getInstance().getReference("collection");
                        String id = databaseUser.push().getKey();
                        databaseUser.child(id).setValue(c);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
        this.clicked = false;

    }

    @Override
    public void onMapReady(GoogleMap gMap) {
        googleMap = gMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //Edit the following as per you needs
        googleMap.setTrafficEnabled(true);
        googleMap.setIndoorEnabled(true);
        googleMap.setBuildingsEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
       // LatLng placeLocation = new LatLng(latitude, longitude);
      /*
               Marker placeMarker = googleMap.addMarker(new MarkerOptions().position(placeLocation)
               .title(""));

               */

    }




}
