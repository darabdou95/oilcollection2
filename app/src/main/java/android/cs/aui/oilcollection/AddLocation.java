package android.cs.aui.oilcollection;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.cs.aui.oilcollection.classes.Shop;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddLocation extends Activity implements View.OnClickListener {
    private EditText name, phone, address, password, confirmpassword,barrels;
    private Button create;
    private String NAME, PHONE, ADDRESS, PASSWORD, CONFIRM;
    private int BAREL;
    double Long, Lat;
    String Longitude, latitude;

    DatabaseReference databaseshops;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managment);
        databaseshops = FirebaseDatabase.getInstance().getReference("shop");
        name = findViewById(R.id.editname);
        name.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        phone = findViewById(R.id.editphone);
        address = findViewById(R.id.editaddress);
        password = findViewById(R.id.editpass);
        confirmpassword = findViewById(R.id.editconfpass);
        barrels = findViewById(R.id.barel);
        create = findViewById(R.id.shopcreate);
        address.setOnClickListener(this);
        create.setOnClickListener(this);
    }

    private void addShop() {
        NAME = name.getText().toString();
        NAME = NAME.toLowerCase();
        PHONE = phone.getText().toString();
        PASSWORD = password.getText().toString();
        BAREL = Integer.parseInt(barrels.getText().toString());
        String id = databaseshops.push().getKey();
        Shop shop = new Shop(id, NAME, PHONE, PASSWORD,ADDRESS, Long, Lat,BAREL);
        databaseshops.child(id).setValue(shop);
    }

    @Override
    public void onClick(final View view) {
        if (view.getId() == R.id.shopcreate) {
            NAME = name.getText().toString();
            PHONE = phone.getText().toString();
            PASSWORD = password.getText().toString();
            CONFIRM = confirmpassword.getText().toString();
            String barel = barrels.getText().toString();

            if (NAME.isEmpty() || PHONE.isEmpty() || ADDRESS == null || PASSWORD.isEmpty() || CONFIRM.isEmpty()|| barel.isEmpty()) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(AddLocation.this);
                builder1.setMessage("Please fill all the fields");
                builder1.setCancelable(true);
                AlertDialog alert11 = builder1.create();
                alert11.show();
                return;
            }
            BAREL = Integer.parseInt(barel);
            if (PHONE.length() != 10 || (!PHONE.startsWith("06") && !PHONE.startsWith("05") && !PHONE.startsWith("07"))) {
                phone.setError("Invalid phone number");
                phone.requestFocus();
                return;
            }
            if (!PASSWORD.equals(CONFIRM)) {
                confirmpassword.setError("Password doesn't match");
                confirmpassword.requestFocus();
                return;
            }
        /*if (PASSWORD.length()<6) {
            password.setError("Password too short ( minimum 6 characters)");
            password.requestFocus();
            return;
        }*/

            if (BAREL<1){
                AlertDialog.Builder builder1 = new AlertDialog.Builder(AddLocation.this);
                builder1.setMessage("The number of barrels must be greater than zero");
                builder1.setCancelable(true);
                AlertDialog alert11 = builder1.create();
                alert11.show();
                return;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to add a location")
                    .setCancelable(false)
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("Sure", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int x) {
                            addShop();
                            Toast.makeText(AddLocation.this, "Location created", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    });
            AlertDialog alert = builder.create();
            alert.show();

        }
            else {
                Intent intent = new Intent(AddLocation.this, MapActivity.class);
                startActivityForResult(intent,2);
            }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            Long = data.getDoubleExtra("longitude", 1);
            Lat = data.getDoubleExtra("latitude", 1);
            Longitude = String.valueOf(Long);
            latitude = String.valueOf(Lat);
            ADDRESS = data.getStringExtra("address");
            address.setText(ADDRESS);

        }


    }
}



