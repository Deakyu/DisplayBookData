package com.example.displaybookdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.displaybookdata.adapter.BookListAdapter;
import com.example.displaybookdata.model.Book;
import com.example.displaybookdata.network.BookService;
import com.example.displaybookdata.network.Client;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private BookListAdapter adapter;
    private RecyclerView recyclerView;
    private BookService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getBookListFromService();
    }

    private void getBookListFromService() {
        service = Client.getRetrofitInstance().create(BookService.class);
        Call<List<Book>> call = service.getBookList();

        Log.wtf("URL CALLED", call.request().url().toString());

        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                generateBookList(response.body());
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateBookList(List<Book> books) {
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new BookListAdapter(this, books);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
