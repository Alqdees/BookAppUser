package com.alqdees.bookapp.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alqdees.bookapp.Adapter.AdapterPdfUser;
import com.alqdees.bookapp.Model.ModelPdf;
import com.alqdees.bookapp.databinding.FragmentBookUserBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookUserFragment extends Fragment {

    private String id;
    private String category;
    private String uid;

    private ArrayList<ModelPdf> pdfArrayList;
    private AdapterPdfUser adapterPdfUser;
    private FragmentBookUserBinding binding;

    public BookUserFragment() {
        // Required empty public constructor
    }
    public static BookUserFragment newInstance(String categoryId, String category,String uid) {
        BookUserFragment fragment = new BookUserFragment();
        Bundle args = new Bundle();
        args.putString("categoryId", categoryId);
        args.putString("category", category);
        args.putString("uid", uid);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getString("categoryId");
            category = getArguments().getString("category");
            uid = getArguments().getString("uid");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBookUserBinding.inflate(LayoutInflater.from(getContext()),container,false);
        if (category.equals("الكل")){
            loadAllBooks();
        }else if (category.equals("الأكثر مشاهدة")){
            loadMostViewDownloadBooks("viewCount");
        }else if (category.equals("الاأكثر تنزيل")){
            loadMostViewDownloadBooks("downloadsCount");
        }else {
            loadCategorizedBooks();
        }
        binding.searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
              try {
                  adapterPdfUser.getFilter().filter(charSequence);
              }
              catch (Exception e){
                  Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
              }

            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return binding.getRoot();
//        return inflater.inflate(R.layout.fragment_book_user, container, false);
    }

    private void loadAllBooks() {
        pdfArrayList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Books");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                pdfArrayList.clear();
                ModelPdf model =null;
                for (DataSnapshot ds : snapshot.getChildren()){

                    model = ds.getValue(ModelPdf.class);
                    pdfArrayList.add(model);
                }
                adapterPdfUser =new AdapterPdfUser(getContext(),pdfArrayList);
                binding.bookRv.setAdapter(adapterPdfUser);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void loadMostViewDownloadBooks(String orderBy) {
        pdfArrayList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Books");
        ref.orderByChild(orderBy)
                .limitToLast(10).
                addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                pdfArrayList.clear();
                for (DataSnapshot ds : snapshot.getChildren()){
                    ModelPdf model = ds.getValue(ModelPdf.class);
                    pdfArrayList.add(model);
                }
                adapterPdfUser =new AdapterPdfUser(getContext(),pdfArrayList);
                binding.bookRv.setAdapter(adapterPdfUser);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void loadCategorizedBooks(){
        pdfArrayList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Books");
        ref.orderByChild("categoryId").equalTo(id).
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        pdfArrayList.clear();
                        for (DataSnapshot ds : snapshot.getChildren()){
                            ModelPdf model = ds.getValue(ModelPdf.class);
                            pdfArrayList.add(model);
                        }
                        adapterPdfUser =new AdapterPdfUser(getContext(),pdfArrayList);
                        binding.bookRv.setAdapter(adapterPdfUser);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
    private void downloadPdfInDevice(String url, String title) throws IOException {
        FirebaseStorage storageReference = FirebaseStorage.getInstance();
        StorageReference islandRef = storageReference.getReferenceFromUrl(url);
//        File localFile = File.createTempFile(title, ".pdf");
        File downloadsFolder = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS+"/"+title);
        String filePath = downloadsFolder.getPath() + "/" + title;

        FileOutputStream out = new FileOutputStream(filePath);
        out.close();

        Toast.makeText(getContext(), "تم الحفظ في المستندات", Toast.LENGTH_SHORT).show();
//        File file = new File(Environment.)
        if (downloadsFolder.exists()){
            islandRef.getFile(downloadsFolder).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getContext(), downloadsFolder.getAbsolutePath(), Toast.LENGTH_SHORT).show();
//                    progressDialog.dismiss();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                    Log.d("ExceptionDownLoad",exception.getMessage());
                }
            });
        }
    }
}