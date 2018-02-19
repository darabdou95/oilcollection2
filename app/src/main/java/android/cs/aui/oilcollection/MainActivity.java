package android.cs.aui.oilcollection;

import android.app.AlertDialog;
import android.content.Intent;
import android.cs.aui.oilcollection.classes.Shop;
import android.cs.aui.oilcollection.classes.Utiles;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private DatabaseReference databaseUser;
    private Button loginBtn;
    private EditText nameText, passText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);
        nameText = findViewById(R.id.nameText);
        passText = findViewById(R.id.passText);
        databaseUser =  FirebaseDatabase.getInstance().getReference("user");
        passText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    loginBtn.callOnClick();
                    return true;
                }
                return false;
            }



        });
    }


    public void logedshop(Shop us){
        Intent intent = new Intent(this, shopActivity.class);
        intent.putExtra("user", us);
        startActivity(intent);
    }
    public void logedCollector(){
        Intent intent = new Intent(this, Collector.class);
        startActivity(intent);
    }
    public void logedManager(){
        Intent intent = new Intent(this, ManagerMenu.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if(!Utiles.isNetworkAvailable(this)){
            Utiles.dialogWithOneButton(this, "No Internet Available");
            return;
        }
        String name = nameText.getText().toString();
        final String pass = passText.getText().toString();
        if(name.isEmpty() || pass.isEmpty()){
            return;
        }

        if(name.equals("collector") && pass.equals("pass")){
            logedCollector();
            return;
        }
        if(name.equals("manager") && pass.equals("pass")){
            logedManager();
            return;
        }

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query = reference.child("shop").orderByChild("shopname").equalTo(name);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Shop us=null;
                if (dataSnapshot.exists()) {
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {

                        us = issue.getValue(Shop.class);
                    }

                    if(us.getPassword().equals(pass) ){
                        //To pass:
                        logedshop(us);

                    }

                    else{
                        Log.e("myError","wrong password");
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                        builder1.setMessage("wrong password or password \n please try again.");
                        builder1.setTitle("login unsuccessful");
                        builder1.show();
                    }
                }
                else{
                    Log.e("myError","declined");
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                    builder1.setMessage("wrong username or password \n please try again.");
                    builder1.setTitle("login unsuccessful");
                    builder1.show();
                }

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
