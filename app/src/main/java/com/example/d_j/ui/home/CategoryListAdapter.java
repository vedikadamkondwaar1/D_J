package com.example.d_j.ui.home;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.d_j.Description;
import com.example.d_j.R;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {
    View listItem;
    private CategoryList[] listdata;

    public CategoryListAdapter(CategoryList[] listdata) {
        this.listdata = listdata;
    }

    @NonNull
    @Override
    public CategoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        listItem = layoutInflater.inflate(R.layout.home_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryListAdapter.ViewHolder holder, int position) {
        final CategoryList myListData = listdata[position];
        holder.catName.setText(listdata[position].getCategoryname());
        holder.imageView.setImageResource(listdata[position].getImgId());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), Description.class);
                v.getContext().startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView catName;
        public ImageView imageView;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.catName = (TextView) itemView.findViewById(R.id.category_name);
            this.imageView = (ImageView) itemView.findViewById(R.id.categori_img);
            this.linearLayout = (LinearLayout)itemView.findViewById(R.id.linearLayout);


        }
    }
}
