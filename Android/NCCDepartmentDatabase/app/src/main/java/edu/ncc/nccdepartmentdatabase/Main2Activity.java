package edu.ncc.nccdepartmentdatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {
    EditText edtName;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        edtName = (EditText) findViewById(R.id.editText);
        btnSearch = (Button) findViewById(R.id.searchBtn);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = edtName.getText().toString();

                Intent BackIntent = new Intent();
                BackIntent.putExtra("KEY",input);
                finish();
            }
        });
    }
}
