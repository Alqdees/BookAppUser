package com.alqdees.bookapp.Activitys;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.documentfile.provider.DocumentFile;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;
import com.alqdees.bookapp.Adapter.AdapterDownload;
import com.alqdees.bookapp.BuildConfig;
import com.alqdees.bookapp.R;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


public class DownloadActivity extends AppCompatActivity {

    private static final int STORAGE_REQUEST_CODE = 101;
    private RecyclerView recyclerView;
    private AdapterDownload adapterDownload;
    private ArrayList<File> arrayList;
    private File [] files;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
//        files = new File[]{};
//         arrayList = new ArrayList<>();
//        arrayList.addAll(Arrays.asList(files));
//        arrayList.clear();
        recyclerView = findViewById(R.id.recycler);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.d("OnCreate","Created");

    }

    @Override
    public void onStart() {
        super.onStart();
        if (files != null){
            return;
        }else {
            getPermissions();
        }

        Log.d("OnStart","OnStart");
//        arrayList.clear();
    }
    private void getPermissions() {
        requestPermission();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S_V2 &&
                    !Environment.isExternalStorageManager()) {
                Uri uri = Uri.parse("package:" + BuildConfig.APPLICATION_ID);
                startActivity(new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, uri));
            } else if (ContextCompat.checkSelfPermission(
                    DownloadActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_GRANTED) {
                getAllBook();

            } else {
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
    }

    @SuppressLint("NotifyDataSetChanged")
    private synchronized void getAllBook() {
        String Path = Environment.getExternalStorageDirectory() + "/" + "كتب مدرسية";
        File file = new File(Path);
        if (!file.exists()){
            Toast.makeText(this, "لاتوجد كتب..", Toast.LENGTH_LONG).show();
        }else {
            files = file.listFiles();
            assert files != null;
            for (File f : files) {
                    f.getName();
                }
            adapterDownload = new AdapterDownload(files, DownloadActivity.this);
            recyclerView.setAdapter(adapterDownload);
            adapterDownload.notifyDataSetChanged();

        }
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
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case STORAGE_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    try {
                        getAllBook();
                    } catch (Exception e) {
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "نحتاج صلاحيات الوصول للذاكرة", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    private void requestPermission()
    {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                STORAGE_REQUEST_CODE);
    }



//    @Override
//    protected void onResume() {
//        super.onResume();
//        arrayList.clear();
//    }
}