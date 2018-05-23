package com.example.displaybookdata.network;

import com.example.displaybookdata.model.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BookService {
    @GET("books.json")
    Call<List<Book>> getBookList();
}
