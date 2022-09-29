package com.alqdees.bookapp.Activitys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.alqdees.bookapp.Adapter.AdapterCategory;

import com.alqdees.bookapp.Model.ModelCategory;

import com.alqdees.bookapp.databinding.ActivityDashboardBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {
    public ArrayList<ModelCategory> categoryArrayList;

    private ActivityDashboardBinding binding;
//    private FirebaseAuth firebaseAuth;
//    private SqliteSave save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupViewPagerAdapter();

    }
    private void setupViewPagerAdapter(){

                categoryArrayList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Categories");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot ds: snapshot.getChildren()){
                    ModelCategory model = ds.getValue(ModelCategory.class);
                    categoryArrayList.add(model);
                    assert model != null;
                }
                AdapterCategory adapterPdfUser = new AdapterCategory(DashboardActivity.this,categoryArrayList);
                binding.recyclerview.hasFixedSize();
                binding.recyclerview.setLayoutManager(new LinearLayoutManager(DashboardActivity.this));
                binding.recyclerview.setAdapter(adapterPdfUser);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(DashboardActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

}