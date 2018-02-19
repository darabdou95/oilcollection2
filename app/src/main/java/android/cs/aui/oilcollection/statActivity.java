package android.cs.aui.oilcollection;

import android.cs.aui.oilcollection.classes.collection;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class statActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView totalText;
    private int i = 0 ;
    private Spinner spinner;
    private EditText text1 , text2 , text3;
    private String[] searches = {"month","year","day","name"};
    private Button searchBtn;
    private EditText searchText;
    private ArrayList<collection> collections;
    private  TextView mySearch ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);
        totalText = findViewById(R.id.totalText);
        spinner = findViewById(R.id.mySpinner);
        text1 = findViewById(R.id.yearText);
        text2 = findViewById(R.id.yearText2);
        text3 = findViewById(R.id.yearText3);
        searchBtn = findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(this);
        searchText = findViewById(R.id.searchText);
        mySearch = findViewById(R.id.mySearch);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,
                        searches); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(new  AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0 ){
                    text1.setEnabled(true);
                    text2.setEnabled(true);

                    text3.setEnabled(false);
                    searchText.setEnabled(false);
                }
                else if(position == 1 ){
                    text1.setEnabled(true);

                    text3.setEnabled(false);
                    text2.setEnabled(false);
                    searchText.setEnabled(false);
                }else if(position == 2 ){
                    text1.setEnabled(true);
                    text3.setEnabled(true);
                    text2.setEnabled(true);
                    searchText.setEnabled(false);
                }
                else if(position == 3 ){
                    text1.setEnabled(false);
                    text2.setEnabled(false);
                    text3.setEnabled(false);
                    searchText.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                text1.setEnabled(false);
                text2.setEnabled(false);
                text3.setEnabled(false);
                searchText.setEnabled(false);
            }

        });



    }

    public void searchByName(int j){

        int i =0;
        String resultR = "";
        for (i=0;i<collections.size();i++) {
            if (j == 1)
                resultR =resultR+collections.get(i).getDay()+"\n";
            else
                resultR =resultR+collections.get(i).getName()+"  "+collections.get(i).getDay()+"\n";
        }

        mySearch.setText(resultR);

    }


    @Override
    public void onClick(View v) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        String selec = searches[spinner.getSelectedItemPosition()];
        Query query;
        final int pos =  spinner.getSelectedItemPosition();
        String tst = "";
        int criteria = 0 ;
        Calendar cal = Calendar.getInstance();
        //query = reference.child("collection").orderByChild(selec).equalTo(criteria);
        switch (spinner.getSelectedItemPosition()){
            case 0 :
                tst = text1.getText().toString()+"-"+text2.getText().toString();
                query = reference.child("collection").orderByChild("month").equalTo(tst);
                break;

            case 1:
                tst = text1.getText().toString();
                query = reference.child("collection").orderByChild("year").equalTo(Integer.parseInt(tst));
                break;

            case 2:
                tst = text1.getText().toString()+"-"+text2.getText().toString()+"-"+text3.getText().toString();
                query = reference.child("collection").orderByChild("day").equalTo(tst);
                break;

            default:
                query = reference.child("collection").orderByChild("name").equalTo(searchText.getText().toString());

                break;

        }
        //query = reference.child("collection").orderByChild("year").equalTo(2017);

        //Query query = reference.child("collection").orderByChild(selec).equalTo(Integer.parseInt(searchText.getText().toString()));
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                i=0;
                if (dataSnapshot.exists()) {
                    collections = new ArrayList<>();
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        collections.add(issue.getValue(collection.class));
                        Log.e("myError",issue.getValue(collection.class).getDay());
                        i++;
                    }

                    if(pos == 3){
                        searchByName(1);
                    }
                    else
                        searchByName(0);
                } else {
                    Log.e("myError", "no pendings");


                }
                totalText.setText(Integer.toString(i));
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
