package com.satyam.booklibrarycrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickInterface{
    MyDatabaseHelper databaseHelper;
    CustomAdapter customAdapter;
    SwipeRefreshLayout swipeRefresh;
    RecyclerView recyclerView;
    ArrayList<String> book_id, book_title, book_author, book_pages;
    private FloatingActionButton add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        swipeRefresh = findViewById(R.id.swipeRefresh);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                addToRecycler();
                swipeRefresh.setRefreshing(false);
            }
        });
        addToRecycler();

    }

    void storeDataInArrays() {
        Cursor cursor = databaseHelper.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(MainActivity.this, "No Data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                book_id.add(cursor.getString(0));
                book_title.add(cursor.getString(1));
                book_author.add(cursor.getString(2));
                book_pages.add(cursor.getString(3));
            }
        }
    }

    void addToRecycler() {
        databaseHelper = new MyDatabaseHelper(MainActivity.this);
        book_id = new ArrayList<>();
        book_title = new ArrayList<>();
        book_author = new ArrayList<>();
        book_pages = new ArrayList<>();
        storeDataInArrays();
        customAdapter = new CustomAdapter(MainActivity.this, this,book_id, book_title, book_author, book_pages);
        recyclerView.setAdapter(customAdapter);
    }

    @Override
    public void onItemClick(int position) {
      Intent intent=new Intent(this,UpdateActivity.class);
        intent.putExtra("id",String.valueOf(book_id.get(position)));
        intent.putExtra("title",String.valueOf(book_title.get(position)));
        intent.putExtra("author",String.valueOf(book_author.get(position)));
        intent.putExtra("pages",String.valueOf(book_pages.get(position)));
      startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDelete();
        }
        return super.onOptionsItemSelected(item);
    }
    void confirmDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All");
        builder.setMessage("Are you sure do you want to delete all ?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                databaseHelper = new MyDatabaseHelper(MainActivity.this);
                databaseHelper.deleteAll();
                recreate();
                Toast.makeText(MainActivity.this, "All Deleted", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();

    }
}