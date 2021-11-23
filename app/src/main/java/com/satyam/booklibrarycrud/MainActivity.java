package com.satyam.booklibrarycrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MyDatabaseHelper databaseHelper;
    CustomAdapter customAdapter;
    RecyclerView recyclerView;
    ArrayList<String> book_id,book_title,book_author,book_pages;
 private FloatingActionButton add_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        add_button=findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });
        databaseHelper= new MyDatabaseHelper(MainActivity.this);
        book_id=new ArrayList<>();
        book_title=new ArrayList<>();
        book_author=new ArrayList<>();
        book_pages=new ArrayList<>();
        storeDataInArrays();
        customAdapter=new CustomAdapter(MainActivity.this,book_id,book_title,book_author,book_pages);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


    }
    void storeDataInArrays(){
        Cursor cursor=databaseHelper.readAllData();
        if(cursor.getCount()==0){
            Toast.makeText(MainActivity.this, "No Data", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                book_id.add(cursor.getString(0));
                book_title.add(cursor.getString(1));
                book_author.add(cursor.getString(2));
                book_pages.add(cursor.getString(3));
            }
        }
    }

}