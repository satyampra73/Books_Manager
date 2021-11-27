package com.satyam.booklibrarycrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    EditText name_input,pages_input,author_input;
    Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        name_input=findViewById(R.id.name_input);
        pages_input=findViewById(R.id.pages_input);
        author_input=findViewById(R.id.author_input);
        btn_add=findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDatabaseHelper=new MyDatabaseHelper(AddActivity.this);
                myDatabaseHelper.addBook(name_input.getText().toString(),author_input.getText().toString()
                        ,Integer.valueOf(pages_input.getText().toString()));
                Intent intent=new Intent(AddActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}