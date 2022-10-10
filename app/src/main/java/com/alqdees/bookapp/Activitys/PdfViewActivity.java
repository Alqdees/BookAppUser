package com.alqdees.bookapp.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.alqdees.bookapp.Constants.Constants;
import com.alqdees.bookapp.databinding.ActivityPdfViewBinding;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;


public class PdfViewActivity extends AppCompatActivity {
    private ActivityPdfViewBinding binding;
    private String pdfId,bookTitle,pdfUrl;
    private int currentPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPdfViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Intent intent = getIntent();
        pdfId = intent.getStringExtra("bookId");
        bookTitle = intent.getStringExtra("bookTitle");
        pdfUrl = intent.getStringExtra("pdfUrl");

//        Log.d("pdfURL",pdfUrl);

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Dexter.withContext(this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                try {
                    checkFolder();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                if (permissionDeniedResponse.isPermanentlyDenied()){
                    Toast.makeText(PdfViewActivity.this,
                            permissionDeniedResponse.getPermissionName(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest,
                                                           PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();

            }
        }).check();
    }
    private void checkFolder() throws IOException {

        File folder = new File(Environment.getExternalStorageDirectory() + "/" + "كتب مدرسية");
        if (folder.exists()) {

                if (checkFile().exists()){
                    Log.d("GETNANE1",checkFile().toString());
                    readPdf(checkFile());
                }else {
                    loadBookFromUrl();;
                }

        }else {
            loadBookFromUrl();;
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
        Log.d("FILE",file.getName());
//       binding.pdfViewer.fromFile(file).load();
            binding.pdfViewer.fromFile(file).swipeHorizontal(false).onPageChange(new OnPageChangeListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onPageChanged(int page, int pageCount) {

                        currentPage = (page + 1) ;
                        binding.PageTv.setText(currentPage + "/"+pageCount);
                }
            }).onPageError(new OnPageErrorListener() {
                @Override
                public void onPageError(int page, Throwable t) {
                    Log.d("Throwable",t.getMessage());
                }
            }).load();
    }

   /* private void loadBookDetails() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Books");
        ref.child(pdfId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String pdfUrl = snapshot.child("url").getValue().toString();
                Log.d("TAG",pdfUrl);
                try {
                    loadBookFromUrl(pdfUrl);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/
    private void loadBookFromUrl() throws MalformedURLException {

        StorageReference ref = FirebaseStorage.getInstance().getReferenceFromUrl(pdfUrl);
        ref.getBytes(Constants.MAX_BYTES_PDF).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                binding.pdfViewer.fromBytes(bytes).swipeHorizontal(false).onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(int page, int pageCount) {
                        int currentPage = (page + 1);
                        binding.PageTv.setText(currentPage + "/"+pageCount);
                        binding.progressBar.setVisibility(View.GONE);
                    }
                }).onPageError(new OnPageErrorListener() {
                    @Override
                    public void onPageError(int page, Throwable t) {
                        Log.d("Throwable",t.getMessage());
                    }
                }).load();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Exception",e.getMessage());
            }
        });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (bookTitle.isEmpty() && bookTitle == null){
//            loadBookDetails();
////            System.exit(0);
//        }
////        loadBookDetails();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (bookTitle.isEmpty() && bookTitle == null){
//            loadBookDetails();
////            System.exit(0);
//        }
//    }
}