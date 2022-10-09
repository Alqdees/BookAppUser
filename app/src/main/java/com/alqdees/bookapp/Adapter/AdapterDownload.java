package com.alqdees.bookapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.alqdees.bookapp.Activitys.PdfViewActivity;
import com.alqdees.bookapp.R;
import com.github.barteksc.pdfviewer.PDFView;
import java.io.File;


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
        holder.pdfView.fromFile(file).load();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, PdfViewActivity.class);
                i.putExtra("bookTitle",name);
                context.startActivity(i);
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
         public Holder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.titleTv);
            pdfView = itemView.findViewById(R.id.pdfViewer);
        }
    }
}
