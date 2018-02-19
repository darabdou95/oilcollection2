package android.cs.aui.oilcollection;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.cs.aui.oilcollection.classes.collectorinfo;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class collectorActivity extends Activity implements View.OnClickListener {
    private EditText name, phone, address;
    private Button create;
    private String NAME, PHONE, ADDRESS;


    DatabaseReference databasecollectors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collector2);
        databasecollectors = FirebaseDatabase.getInstance().getReference("collectorinfo");
        name = findViewById(R.id.editname);
        name.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        phone = findViewById(R.id.editphone);
        address = findViewById(R.id.editaddress);
        create = findViewById(R.id.collectorcreate);
        create.setOnClickListener(this);
    }
    private void addcollector() {
        NAME = name.getText().toString();
        PHONE = phone.getText().toString();
        ADDRESS=address.getText().toString();
        String id = databasecollectors.push().getKey();
        collectorinfo info = new collectorinfo(id, NAME, PHONE,ADDRESS);
        databasecollectors.child(id).setValue(info);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.collectorcreate) {
            NAME = name.getText().toString();
            PHONE = phone.getText().toString();
            ADDRESS=address.getText().toString();
            if (NAME.isEmpty() || PHONE.isEmpty() || ADDRESS.isEmpty()) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(collectorActivity.this);
                builder1.setMessage("Please fill all the fields");
                builder1.setCancelable(true);
                AlertDialog alert11 = builder1.create();
                alert11.show();
                return;
            }
            if (PHONE.length() != 10) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(collectorActivity.this);
                builder1.setMessage("The phone must be 10 digits");
                builder1.setCancelable(true);
                AlertDialog alert11 = builder1.create();
                alert11.show();
                return;
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to add a collector")
                    .setCancelable(false)
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("Sure", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int x) {
                            addcollector();
                            Toast.makeText(collectorActivity.this, "collector Added", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    });
            AlertDialog alert = builder.create();
            alert.show();

        }
        else {
           return;
        }

    }
}
