package com.alqdees.bookapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.alqdees.bookapp.Activitys.PdfListAdminActivity;
import com.alqdees.bookapp.Model.ModelCategory;
import com.alqdees.bookapp.databinding.RowCategoryBinding;
import java.util.ArrayList;
import java.util.HashMap;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.HolderCategory>{
    private Context context;
    public ArrayList<ModelCategory> categoryArrayList,filterList;
    private RowCategoryBinding binding;
    private HashMap<String, String> values;



    public AdapterCategory(Context context, ArrayList<ModelCategory> categoryArrayList) {
        this.context = context;
        this.categoryArrayList = categoryArrayList;
        this.filterList = categoryArrayList;
    }

    @NonNull
    @Override
    public HolderCategory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = RowCategoryBinding.inflate(LayoutInflater.from(context),parent,false);
        return new HolderCategory(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull HolderCategory holder, int position) {
        ModelCategory model = categoryArrayList.get(position);
        String id = model.getId();
        String category = model.getCategory();
        String uid = model.getUid();
        String timestamp = model.getTimestamp();

        holder.catTv.setText(category);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PdfListAdminActivity.class);
                intent.putExtra("categoryId",id);
                intent.putExtra("categoryTitle",category);
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }


    class HolderCategory extends RecyclerView.ViewHolder{

        TextView catTv;


        public HolderCategory(@NonNull View itemView) {

            super(itemView);
            catTv = binding.categoryTv;
        }
    }
}
