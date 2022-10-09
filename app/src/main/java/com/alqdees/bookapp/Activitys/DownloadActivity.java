package com.alqdees.bookapp.Activitys;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
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
import android.util.Log;
import android.widget.Toast;
import com.alqdees.bookapp.Adapter.AdapterDownload;
import com.alqdees.bookapp.BuildConfig;
import com.alqdees.bookapp.R;
import java.io.File;
import java.util.Objects;


public class DownloadActivity extends AppCompatActivity {

    private static final int STORAGE_REQUEST_CODE = 101;
    private RecyclerView recyclerView;
    private AdapterDownload adapterDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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


    private void getAllBook() {
        String Path = Environment.getExternalStorageDirectory() + "/" + "كتب مدرسية";
        File file = new File(Path);
        File[] files = file.listFiles();
        for (int i = 0; i< Objects.requireNonNull(files).length; i++)
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
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case STORAGE_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    try {
                        getAllBook();
                    } catch (Exception e) {
                        Log.d("" + e.getMessage(), "GRANTED");
                    }
                } else {
                    Toast.makeText(this, "نحتاج صلاحيات للذاكرة", Toast.LENGTH_LONG).show();
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

    @Override
    protected void onStart() {
        super.onStart();
        getPermissions();
    }
}