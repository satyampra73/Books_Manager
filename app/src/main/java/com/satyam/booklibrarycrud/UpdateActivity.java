package com.satyam.booklibrarycrud;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText titleInput, authorInput, pagesInput;
    Button updateButton, btn_delete;
    String id, title, author, pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        titleInput = findViewById(R.id.name_input2);
        authorInput = findViewById(R.id.author_input2);
        pagesInput = findViewById(R.id.pages_input2);
        btn_delete = findViewById(R.id.btn_delete);
        updateButton = findViewById(R.id.btn_add2);
        getIntentData();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(title);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDelete();

            }
        });
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(UpdateActivity.this);
                title = titleInput.getText().toString().trim();
                author = authorInput.getText().toString().trim();
                pages = pagesInput.getText().toString().trim();
                myDatabaseHelper.updateData(id, title, author, pages);
                Intent intent=new Intent(UpdateActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });


    }

    void getIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("author") && getIntent().hasExtra("pages")) {
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");
            titleInput.setText(title);
            authorInput.setText(author);
            pagesInput.setText(pages);
        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title);
        builder.setMessage("Do You want to delete " + title + " ?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper databaseHelper = new MyDatabaseHelper(UpdateActivity.this);
                databaseHelper.deleteOneRow(id);
                finish();
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
