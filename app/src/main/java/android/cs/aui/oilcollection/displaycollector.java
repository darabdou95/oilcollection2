package android.cs.aui.oilcollection;

import android.cs.aui.oilcollection.classes.collectorinfo;
import android.cs.aui.oilcollection.classes.collectorinfoadapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class displaycollector extends AppCompatActivity {
    private ArrayList<collectorinfo> collector;
    private ListView myListView;
    private collectorinfoadapter adapter;
    private int selected = -2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaycollector);
        collector = new ArrayList<collectorinfo>();
        myListView =  findViewById(R.id.listList);
        createListView();
    }
    public void createListView (){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query = reference.child("collectorinfo").orderByChild("name");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                collectorinfo us = null;
                if (dataSnapshot.exists()) {

                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        us = issue.getValue(collectorinfo.class);
                        collector.add(us);
                    }


                } else {
                    Toast.makeText(displaycollector.this, "No collectors", Toast.LENGTH_SHORT).show();
                    displaycollector.this.finish();
                }
                /////////use thus user here/////////////
                ///// us.getLatitude
                /////////////////////



                adapter = new collectorinfoadapter(displaycollector.this,collector);
                myListView.setAdapter(adapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
}
