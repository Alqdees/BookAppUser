package com.alqdees.bookapp.Activitys;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.widget.Toast;

import com.alqdees.bookapp.Adapter.AdapterDownload;
import com.alqdees.bookapp.Constants.MyApplication;
import com.alqdees.bookapp.R;
import com.google.firebase.installations.BuildConfig;

import java.io.File;
import java.io.IOException;

public class DownloadActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterDownload adapterDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getPermissions();
    }

    private void getPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R &&
                !Environment.isExternalStorageManager()) {
            Uri uri = Uri.fromParts("package:",this.getPackageName(),null);
            startActivity(new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION,
                    uri));
            getAllBook();
        }
        else if (ContextCompat.checkSelfPermission(
                DownloadActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)==
                PackageManager.PERMISSION_GRANTED){
           getAllBook();
        }
        else {
            requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

    }

    private void getAllBook() {
        String Path = Environment.getExternalStorageDirectory() + "/" + "كتب مدرسية";
        File file = new File(Path);
        File[] files = file.listFiles();
        assert files !=null;
        for (int i =0;i<files.length;i++)
        {
            files[i].getName();
        }
        adapterDownload = new AdapterDownload(files,DownloadActivity.this);
        recyclerView.setAdapter(adapterDownload);

    }
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted){

                        getAllBook();

                }else {
                    Toast.makeText(DownloadActivity.this,
                            "يحتاج امكانية الوصول",
                            Toast.LENGTH_SHORT).show();
                }
            });
}