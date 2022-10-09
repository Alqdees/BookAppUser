package com.alqdees.bookapp.Activitys;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.MotionButton;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.alqdees.bookapp.R;



public class SplashActivity extends AppCompatActivity {
//    private FirebaseAuth firebaseAuth;
    private MotionButton allBook,download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        firebaseAuth = FirebaseAuth.getInstance();
        allBook = findViewById(R.id.allBook);
        download = findViewById(R.id.downLoad);
        allBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this,DashboardActivity.class));
            }
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        SplashActivity.this,
                        DownloadActivity.class
                ));
            }
        });

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                checkUser();
//            }
//        },3000);
    }

    private void checkUser() {
//        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//        if (firebaseUser ==null){
            startActivity(new Intent(SplashActivity.this,DashboardActivity.class));
            finish();
//        }
//        else
//        {
//            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
//            reference.child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                    String usertype = (String) snapshot.child("usertype").getValue();
//                    if (usertype.equals("user")){
//                        startActivity(new Intent(
//                                SplashActivity.this,DashboardActivity.class));
//                        finish();
//                    }
//                    else if (usertype.equals("admin")){
//                        startActivity(new Intent(
//                                SplashActivity.this,DashboardAdminActivity.class));
//                        finish();
//                    }
//                    else {
//                        Toast.makeText(
//                                SplashActivity.this,
//                                "لايوجد حساب مطابق ",
//                                Toast.LENGTH_LONG).show();
//                        finish();
//                    }
//                }
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                    Toast.makeText(SplashActivity.this,
//                            ""+error.getMessage(),
//                            Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
    }
}