package com.cavista.sample.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cavista.sample.R;
import com.cavista.sample.domain.model.CommentDataModel;

import java.util.ArrayList;
import java.util.List;

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.CommentViewHolder> {
    private List<CommentDataModel> listdata = new ArrayList<>();

    // RecyclerView recyclerView;
    public void setList(List<CommentDataModel> listdata) {
        this.listdata.clear();
        this.listdata.addAll(listdata);
        notifyDataSetChanged();
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.comment_item, parent, false);
        CommentViewHolder viewHolder = new CommentViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        final CommentDataModel commentDataModel = listdata.get(position);
        holder.textView.setText(commentDataModel.getMsg());
        holder.dateView.setText(commentDataModel.getDate());
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public TextView dateView;

        public CommentViewHolder(View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.msgText);
            dateView = itemView.findViewById(R.id.dateView);
        }
    }
}