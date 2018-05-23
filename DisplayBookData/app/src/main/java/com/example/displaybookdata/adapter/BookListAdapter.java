package com.example.displaybookdata.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.displaybookdata.R;
import com.example.displaybookdata.model.Book;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CardView row;
        public TextView author;
        public TextView title;
        public ImageView thumbnail;

        public ViewHolder(View iv) {
            super(iv);

            row = iv.findViewById(R.id.book_row);
            author = iv.findViewById(R.id.author);
            title = iv.findViewById(R.id.title);
            thumbnail = iv.findViewById(R.id.thumbnail);
        }
    }

    private final LayoutInflater inflater;
    private List<Book> books;
    private Context c;

    public BookListAdapter(Context c, List<Book> books) {
        this.inflater = LayoutInflater.from(c);
        this.c = c;
        this.books = books;
    }

    @Override
    public BookListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.book_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookListAdapter.ViewHolder vh, int pos) {
        Book curBook = books.get(pos);
        String title = isNull(curBook.getTitle()) ? "Not available" : curBook.getTitle();
        String author = isNull(curBook.getAuthor()) ? "Not available" : curBook.getAuthor();

        vh.row.setUseCompatPadding(true);
        vh.title.setText(title);
        vh.author.setText(author);
        Picasso.with(c).load(curBook.getImageURL()).resize(100, 150).centerCrop().into(vh.thumbnail);
    }

    private boolean isNull(String str) {
        return str == null || str.equals("");
    }

    public void setBooks(List<Book> newBooks) {
        this.books = newBooks;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() { return books != null ? books.size() : 0; }
}
