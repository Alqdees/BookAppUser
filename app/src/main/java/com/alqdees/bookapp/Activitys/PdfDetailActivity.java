package com.alqdees.bookapp.Activitys;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.alqdees.bookapp.Constants.MyApplication;
import com.alqdees.bookapp.R;
import com.alqdees.bookapp.databinding.ActivityPdfDetailBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;
import com.alqdees.bookapp.BuildConfig;

public class PdfDetailActivity extends AppCompatActivity {

    private static final int STORAGE_REQUEST_CODE = 100;
    private ActivityPdfDetailBinding binding;
     private String bookTitle,bookUrl;
    boolean isInMyFavorite = false;
    private MyApplication application;
//    private FirebaseAuth firebaseAuth;
    private String bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPdfDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        application = new MyApplication();
        Intent intent = getIntent();
        bookId = intent.getStringExtra("bookId");
//        firebaseAuth = FirebaseAuth.getInstance();
//        if (firebaseAuth.getCurrentUser() != null){
//            checkIsFavorite();
//        }
        binding.readPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PdfDetailActivity.this,PdfViewActivity.class);
                intent.putExtra("bookId",bookId);
                intent.putExtra("bookTitle",bookTitle);
                startActivity(intent);
            }
        });
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        loadBookDetails();
        MyApplication.incrementBookViewCount(bookId);

        binding.downLoad.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.R)
            @Override
            public void onClick(View view) {
                try {
                   permission();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

//        binding.favoriteBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (firebaseAuth.getCurrentUser() == null){
//                    Toast.makeText(PdfDetailActivity.this, "لم تسجل الدخول", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    if (isInMyFavorite){
//                        MyApplication.removeFromFavorite(PdfDetailActivity.this,bookId);
//                    }
//                    else {
//                        MyApplication.addToFavorite(PdfDetailActivity.this,bookId);
//                    }
//                }
//            }
//        });

    }

    private void checkFolder() throws IOException {

        File folder = new File(Environment.getExternalStorageDirectory() + "/" + "كتب مدرسية");
        if (folder.exists()) {
            if (checkFile())
            {
                Toast.makeText(
                        PdfDetailActivity.this,
                        "هذا الكتاب موجود",
                        Toast.LENGTH_SHORT).show();
                return;
            }else {
                downloadFile();
            }

        }else {
            downloadFile();
        }
    }

    private boolean checkFile() throws IOException {
       boolean isExists =false;
        String Path = Environment.getExternalStorageDirectory() + "/" + "كتب مدرسية";
        File file = new File(Path);
        File[] files = file.listFiles();
        assert files != null;
        for (File f : files) {
            if (f.getName().contains(bookTitle)) {
                isExists = true;
                break;
            }
        }
        return isExists;
    }

    private void downloadFile() throws IOException {
//        MyApplication.downloadBook(PdfDetailActivity.this,bookId,bookTitle,bookUrl);
        MyApplication.downloadBook(PdfDetailActivity.this,bookId,bookTitle,bookUrl);
    }

    private void permission() throws IOException {

        requestPermission();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R &&
                !Environment.isExternalStorageManager()) {
            Uri uri = Uri.parse("package:" + BuildConfig.APPLICATION_ID);
            startActivity(new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, uri));
            checkFolder();
        }
        else if (ActivityCompat.checkSelfPermission(
                PdfDetailActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)==
                PackageManager.PERMISSION_GRANTED){
            checkFolder();
        }
        else {
            requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        }

    }
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(),isGranted -> {
                if (isGranted){
                    try {
                        checkFolder();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(PdfDetailActivity.this, "يحتاج امكانية الوصول", Toast.LENGTH_SHORT).show();
                }
            });

    private void loadBookDetails() {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Books");
        ref.child(bookId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                bookId = ""+snapshot.child("id").getValue();
                 bookTitle = ""+snapshot.child("title").getValue();
                String description = ""+snapshot.child("description").getValue();
                String categoryId = ""+snapshot.child("categoryId").getValue();
                bookUrl = ""+snapshot.child("url").getValue();
                String timestamp = ""+snapshot.child("timestamp").getValue();
                String viewsCount = ""+ snapshot.child("viewCount").getValue();
                String downloadsCount = ""+snapshot.child("downloadsCount").getValue();
//                binding.downLoad.setVisibility(View.VISIBLE);

                String date = formatTimeStamp(Long.parseLong(timestamp));

//                MyApplication.loadCategory(""+categoryId, binding.categoryTv);

//                MyApplication.loadPdfFromUrlSinglePage(""+bookUrl,binding.pdfViewer,binding.progressBar,binding.pageTv);
//                loadPdfSize(bookUrl,binding.sizeTv);
//                  loadPdfSize(""+url,binding.sizeTv);
//                MyApplication.loadPdfSize(""+bookUrl,binding.sizeTv);

//
//                binding.sizeTv.setText(bookUrl);
               binding.titleTv.setText(bookTitle);
             binding.descriptionTv.setText(description);
                binding.viewTv.setText(viewsCount);
                binding.downloadTv.setText(downloadsCount);
            binding.dateTv.setText(date);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public static final String formatTimeStamp(long timestamp){
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(timestamp);

        return DateFormat.format("dd/MM/yyyy",calendar).toString();
    }




//    private void checkIsFavorite() {
//
//            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
//            reference
//                    .child(Objects.requireNonNull(firebaseAuth.getUid()))
//                    .child("Favorites")
//                    .child(bookId)
//                    .addValueEventListener(new ValueEventListener()
//            {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    isInMyFavorite = snapshot.exists();
//                    if (isInMyFavorite) {
//                        binding.favoriteBtn.setCompoundDrawablesRelativeWithIntrinsicBounds
//                                (0, R.drawable.ic_favorite, 0, 0);
//                        binding.favoriteBtn.setText("أزالة من المفضلة");
//                    } else {
//                        binding.favoriteBtn.setCompoundDrawablesRelativeWithIntrinsicBounds
//                                (0, R.drawable.ic_favorite_white, 0, 0);
//
//                        binding.favoriteBtn.setText("أضافة الى المفضلة");
//
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//        }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case STORAGE_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    try {
                       checkFolder();
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
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                STORAGE_REQUEST_CODE);
    }
    }