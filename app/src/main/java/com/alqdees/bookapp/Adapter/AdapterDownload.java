package com.alqdees.bookapp.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.documentfile.provider.DocumentFile;
import androidx.recyclerview.widget.RecyclerView;

import com.alqdees.bookapp.Activitys.DownloadActivity;
import com.alqdees.bookapp.Activitys.PdfViewActivity;
import com.alqdees.bookapp.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;

import java.io.File;
import java.net.URI;
import java.nio.file.FileSystem;
import java.util.ArrayList;
import java.util.Arrays;


public class AdapterDownload extends RecyclerView.Adapter<AdapterDownload.Holder> {
    private File[] files;
    private Context context;
    public AdapterDownload (File[] arrayList , Context context){
        this.files = arrayList;
        this.context= context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.downloadcards,parent,false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        File file = files[position];
        String name = file.getName();

        holder.tv_name.setText(name);
        holder.pdfView.fromFile(file).swipeHorizontal(false).enableAnnotationRendering(true).onPageChange(new OnPageChangeListener() {
            @Override
            public void onPageChanged(int page, int pageCount) {

            }
        })
                        .onPageError(new OnPageErrorListener() {
                            @Override
                            public void onPageError(int page, Throwable t) {

                            }
                        }).load();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, PdfViewActivity.class);
                i.putExtra("bookTitle",name);
                context.startActivity(i);
//                ((DownloadActivity)context).finish();
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {

            }
        });
    }
    @Override
    public int getItemCount() {
        return files.length;
    }

     class Holder extends RecyclerView.ViewHolder {
        private TextView tv_name;
        private PDFView pdfView;
        private ImageButton delete;
         public Holder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.titleTv);
            pdfView = itemView.findViewById(R.id.pdfViewer);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}
