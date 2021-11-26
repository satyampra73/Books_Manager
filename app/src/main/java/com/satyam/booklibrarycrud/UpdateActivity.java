package com.satyam.booklibrarycrud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText titleInput, authorInput, pagesInput;
    Button updateButton;
    String id, title, author, pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        titleInput = findViewById(R.id.name_input2);
        authorInput = findViewById(R.id.author_input2);
        pagesInput = findViewById(R.id.pages_input2);
        updateButton = findViewById(R.id.btn_add2);
        getIntentData();
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(UpdateActivity.this);
                title=titleInput.getText().toString().trim();
                author=authorInput.getText().toString().trim();
                pages=pagesInput.getText().toString().trim();
                myDatabaseHelper.updateData(id, title, author, pages);

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
}
