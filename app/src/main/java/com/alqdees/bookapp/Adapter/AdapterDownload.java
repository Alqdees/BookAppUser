package com.alqdees.bookapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.alqdees.bookapp.Activitys.PdfViewActivity;
import com.alqdees.bookapp.R;
import com.github.barteksc.pdfviewer.PDFView;
import java.io.File;


public class AdapterDownload extends RecyclerView.Adapter<AdapterDownload.Holder> {
    private File[] files;
    private Context context;
    private File file;
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

         file = files[position];
        String name = file.getName();

        holder.tv_name.setText(name);
        holder.pdfView.fromFile(file).swipeHorizontal(false).load();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, PdfViewActivity.class);
                i.putExtra("bookTitle",name);
                context.startActivity(i);
//                ((DownloadActivity)context).finish();
            }
        });
//        holder.delete.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void onClick(View v) {
//                String Path = Environment.getExternalStorageDirectory() + "/" + "كتب مدرسية"+"/"+name;
//                file = new File(context.getFilesDir() + Path);
//                if (context.getApplicationContext().deleteFile(name)){
//                    notifyDataSetChanged();
//                }else {
//                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
//                }
////                File dir = ;
//                File file = new File(context.getFilesDir(), name);
//                boolean deleted = file.delete();
//                if (deleted){
//                    notifyDataSetChanged();
//                }
//            }
//        });
    }
    @Override
    public int getItemCount() {
        return files.length;
    }

     class Holder extends RecyclerView.ViewHolder {
        private TextView tv_name;
        private PDFView pdfView;
//        private ImageButton delete;
         public Holder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.titleTv);
            pdfView = itemView.findViewById(R.id.pdfViewer);
//            delete = itemView.findViewById(R.id.delete);
        }
    }
}
