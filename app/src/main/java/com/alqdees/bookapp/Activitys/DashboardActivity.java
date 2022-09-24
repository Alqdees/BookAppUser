package com.alqdees.bookapp.Activitys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alqdees.bookapp.Constants.MyApplication;
import com.alqdees.bookapp.Fragment.BookUserFragment;
import com.alqdees.bookapp.Model.ModelCategory;
import com.alqdees.bookapp.Sqlite.SqliteSave;
import com.alqdees.bookapp.databinding.ActivityDashboardBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {
    public ArrayList<ModelCategory> categoryArrayList;
    public ViewPagerAdapter viewPagerAdapter;
    private ActivityDashboardBinding binding;
//    private FirebaseAuth firebaseAuth;
    private SqliteSave save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        save = new SqliteSave(this);

//        MyApplication.downloadBook(this);
//        firebaseAuth = FirebaseAuth.getInstance();
//        checkUser();
        setupViewPagerAdapter(binding.viewPager);
        binding.tableLayout.setupWithViewPager(binding.viewPager);
//        binding.powerBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                firebaseAuth.signOut();
////             startActivity( new Intent(DashboardActivity.this,MainActivity.class));
//             finish();
//            }
//        });

//        binding.profileBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(DashboardActivity.this,ProfileActivity.class));
//            }
//        });
    }
    private void setupViewPagerAdapter(ViewPager viewPager){
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.
                BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, this);
                categoryArrayList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Categories");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                categoryArrayList.clear();
                ModelCategory modelAll =
                        new ModelCategory
                                ("01",
                                        "الكل",
                                        "",
                                        "1");
                ModelCategory modelMostView  = new ModelCategory(
                        "02",
                        "الأكثر مشاهدة",
                        "",
                        "1");
                ModelCategory modelMostDownload  =
                        new ModelCategory(
                        "03",
                        "الاأكثر تنزيل",
                        "",
                        "1");
                categoryArrayList.add(modelAll);
                categoryArrayList.add(modelMostView);
                categoryArrayList.add(modelMostDownload);
                viewPagerAdapter.addFragment(BookUserFragment.newInstance(
                        ""+modelAll.getId()
                        ,""+modelAll.getCategory(),
                        ""+modelAll.getUid()),
                        modelAll.getCategory()
                );
                ////////////
                viewPagerAdapter.addFragment(BookUserFragment.newInstance(
                        ""+modelMostView.getId()
                        ,""+modelMostView.getCategory(),
                        ""+modelMostView.getUid()),modelMostView.getCategory()
                );
                ///////////////
                viewPagerAdapter.addFragment(BookUserFragment.newInstance(
                        ""+modelMostDownload.getId()
                        ,""+modelMostDownload.getCategory(),
                        ""+modelMostDownload.getUid()),
                        modelMostDownload.getCategory()

                );
                viewPagerAdapter.notifyDataSetChanged();
                for (DataSnapshot ds: snapshot.getChildren()){
                    ModelCategory model = ds.getValue(ModelCategory.class);
                    categoryArrayList.add(model);
                    assert model != null;
                    viewPagerAdapter.addFragment(BookUserFragment.newInstance(""+model.getId()
                            ,""+model.getCategory()
                            ,""+model.getUid()),model.getCategory());
                    viewPagerAdapter.notifyDataSetChanged();
//                    downloadDPF();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        viewPager.setAdapter(viewPagerAdapter);

    }
    public static class ViewPagerAdapter extends FragmentPagerAdapter {

        private final ArrayList<BookUserFragment> fragmentList = new ArrayList<>();
        private final ArrayList<String> fragmentTitleList = new ArrayList<>();
        private Context context;

        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior,Context context) {
            super(fm, behavior);
            this.context = context;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
        private void addFragment(BookUserFragment fragment,String title){
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }
    }
//    private void downloadDPF()
//    {
//        FirebaseStorage storageReference = FirebaseStorage.getInstance();
//        StorageReference islandRef = storageReference.getReferenceFromUrl();
//        File localFile = File.createTempFile("Documents", bookTitle+".pdf");
//        if (localFile.exists()){
//            islandRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                    Toast.makeText(context, localFile.getPath(), Toast.LENGTH_SHORT).show();
//                    progressDialog.dismiss();
//
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception exception) {
//                    // Handle any errors
//                    Log.d("ExceptionDownLoad",exception.getMessage());
//                }
//            });
//        }
//
//    }
//

//    private void checkUser() {
//        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//        if (firebaseUser == null){
////            binding.tvSubtitle.setText("لم تقم بتسجيل الدخول");
//        }else{
//            String email =firebaseUser.getEmail();
//            binding.tvSubtitle.setText(email);
//        }
//    }
}