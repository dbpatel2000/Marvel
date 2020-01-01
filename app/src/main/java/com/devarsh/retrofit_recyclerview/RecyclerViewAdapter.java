package com.devarsh.retrofit_recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.LinkedList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Hero> heroList;

    public RecyclerViewAdapter(List<Hero> heroList) {
        this.heroList = heroList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.recycler_view_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Hero heroData = heroList.get(position);
        holder.name.setText(heroData.getName().toString());
        holder.realName.setText(heroData.getRealname().toString());
        Context context = holder.imageView.getContext();
        Picasso.get().load(heroData.getImageurl().toString()).resize(80,80).centerCrop().into(holder.imageView);
        holder.team.setText(heroData.getTeam().toString());
    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView name;
        public TextView realName;
        public TextView team;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.recycler_view_image);
            this.name = itemView.findViewById(R.id.recycler_view_name);
            this.realName = itemView.findViewById(R.id.recycler_view_bio);
            this.team = itemView.findViewById(R.id.recycler_view_team);
        }
    }
}
