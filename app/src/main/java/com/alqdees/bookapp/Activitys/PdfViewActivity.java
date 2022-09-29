package com.alqdees.bookapp.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.alqdees.bookapp.Constants.Constants;
import com.alqdees.bookapp.Constants.MyApplication;
import com.alqdees.bookapp.databinding.ActivityPdfViewBinding;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

public class PdfViewActivity extends AppCompatActivity {
    private ActivityPdfViewBinding binding;
    private String pdfId,bookTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPdfViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        pdfId = intent.getStringExtra("bookId");
        bookTitle = intent.getStringExtra("bookTitle");
        Log.d("BookTitle",bookTitle);
        try {
            checkFolder();
        } catch (IOException e) {
            e.printStackTrace();
        }

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    private void checkFolder() throws IOException {

        File folder = new File(Environment.getExternalStorageDirectory() + "/" + "كتب مدرسية");
        if (folder.exists()) {

                if (checkFile().isFile()){
                    Log.d("GETNANE1",checkFile().toString());
                    readPdf(checkFile());
                }else {
                    loadBookDetails();
                }

        }else {
            loadBookDetails();
        }
    }
    private File checkFile() throws IOException {
        String Path = Environment.getExternalStorageDirectory() + "/" + "كتب مدرسية";
        File file = new File(Path);
        File[] files = file.listFiles();
        assert files != null;
        for (File f : files) {
            if (f.getName().contains(bookTitle)) {
                 file =f;
                binding.progressBar.setVisibility(View.GONE);
                break;
            }
        }
        return file;
    }

    private void readPdf(File file) {
//        Log.d("FILE",file.getName());
//       binding.pdfViewer.fromFile(file).load();
            binding.pdfViewer.fromFile(file).swipeHorizontal(false).onPageChange(new OnPageChangeListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onPageChanged(int page, int pageCount) {
                    int currentPage = (page + 1);
                    binding.PageTv.setText(currentPage + "/"+pageCount);
                }
            }).onPageError(new OnPageErrorListener() {
                @Override
                public void onPageError(int page, Throwable t) {
                    Log.d("Throwable",t.getMessage());
                }
            }).load();
    }

    private void loadBookDetails() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Books");
        ref.child(pdfId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String pdfUrl = Objects.requireNonNull(snapshot.child("url").getValue()).toString();
                loadBookFromUrl(pdfUrl);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void loadBookFromUrl(String pdfUrl) {
        StorageReference ref = FirebaseStorage.getInstance().getReferenceFromUrl(pdfUrl);
        ref.getBytes(Constants.MAX_BYTES_PDF).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                binding.pdfViewer.fromBytes(bytes).swipeHorizontal(false).onPageChange(new OnPageChangeListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onPageChanged(int page, int pageCount) {
                        int currentPage = (page + 1);
                        binding.PageTv.setText(currentPage + "/"+pageCount);
//                        MyApplication.down;
                    }
                }).onError(new OnErrorListener() {
                    @Override
                    public void onError(Throwable t) {
                        Toast.makeText(PdfViewActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                        .onPageError(new OnPageErrorListener() {
                            @Override
                            public void onPageError(int page, Throwable t) {
                                Toast.makeText(PdfViewActivity.this, "خطأ في الصفحة"+page+" "+t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .load();
                binding.progressBar.setVisibility(View.GONE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
//        try {
////            checkFolder();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}