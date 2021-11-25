package com.satyam.booklibrarycrud;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private Context context;
    private ArrayList<String> book_id, book_title, book_author, book_pages;
    RecyclerViewClickInterface recyclerViewClickInterface;

    public CustomAdapter(Context context,RecyclerViewClickInterface recyclerViewClickInterface, ArrayList book_id, ArrayList book_title, ArrayList book_author, ArrayList book_pages) {
        this.context = context;
        this.recyclerViewClickInterface=recyclerViewClickInterface;
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_pages = book_pages;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.txt_bookId.setText(String.valueOf(book_id.get(position)));
        holder.txt_title.setText(String.valueOf(book_title.get(position)));
        holder.txt_Author.setText(String.valueOf(book_author.get(position)));
        holder.txt_pages.setText(String.valueOf(book_pages.get(position)));
//
//        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//       Intent intent=new Intent(context,UpdateActivity.class);
//        intent.putExtra("id",String.valueOf(book_id.get(position)));
//        intent.putExtra("title",String.valueOf(book_title.get(position)));
//        intent.putExtra("author",String.valueOf(book_author.get(position)));
//        intent.putExtra("pages",String.valueOf(book_pages.get(position)));
//        context.startActivity(intent);
//    }
//        });
    }

    @Override
    public int getItemCount() {
        return book_id.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView txt_bookId, txt_Author, txt_title, txt_pages;
//        LinearLayout mainLayout;

        public CustomViewHolder(View itemView) {
            super(itemView);
            txt_bookId = itemView.findViewById(R.id.txt_bookId);
            txt_Author = itemView.findViewById(R.id.txt_bookAuthor);
            txt_title = itemView.findViewById(R.id.txt_book_title);
            txt_pages = itemView.findViewById(R.id.pages);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerViewClickInterface.onItemClick(getAdapterPosition());
                }
            });
        }
    }

}
