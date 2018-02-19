package android.cs.aui.oilcollection;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.cs.aui.oilcollection.classes.Shop;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class shopActivity extends AppCompatActivity implements View.OnClickListener {
    private Shop us;
    private Button fullBtn,end;
    private Shop s = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        Intent i = getIntent();
        us = (Shop) i.getSerializableExtra("user");
        fullBtn = findViewById(R.id.fullBtn);
        fullBtn.setBackground(getDrawable(R.drawable.full));
        fullBtn.setOnClickListener(this);
        end =findViewById(R.id.end);
        end.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        /*DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query = reference.child("shop").orderByChild("shopname").equalTo(us.getShopname());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        s = issue.getValue(Shop.class);

                    }

                } else {

                }

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        if (s.isPending()){
            AlertDialog.Builder builder = new AlertDialog.Builder(shopActivity.this);
            builder.setMessage("the collector was already informed, please wait")
                    .setCancelable(true);

            AlertDialog alert = builder.create();
            alert.show();
            return;
        }
*/if(v==fullBtn){
            if(us.isPending()){
                AlertDialog.Builder builder = new AlertDialog.Builder(shopActivity.this);
                builder.setMessage("شكرا لإخبارنا سوف يتم تفريغ البرميل في أقرب الآجال")
                        .setCancelable(true);

                AlertDialog alert = builder.create();
                alert.show();
                return;

            }

            AlertDialog.Builder builder = new AlertDialog.Builder(shopActivity.this);
            builder.setMessage("هل أنت متأكد من تفريغ البرميل ؟")
                    .setCancelable(false)
                    .setNegativeButton("لا", null)
                    .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int view) {
                            DatabaseReference databaseUser;
                            databaseUser = FirebaseDatabase.getInstance().getReference("shop");
                            us.setPending(true);
                            databaseUser.child(us.getId()).setValue(us);
                            fullBtn.setBackground(getDrawable(R.drawable.empty));
                            AlertDialog.Builder builder = new AlertDialog.Builder(shopActivity.this);
                            builder.setMessage("شكرا لإخبارنا سوف يتم تفريغ البرميل في أقرب الآجال")
                                    .setCancelable(true);

                            AlertDialog alert = builder.create();
                            alert.show();
                        }

                    });

            AlertDialog alert = builder.create();
            alert.show();

        }
     else{
            this.finish();        }

    }
}
