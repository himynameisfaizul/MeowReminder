package com.codemeister.meowreminder.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.codemeister.meowreminder.R;
import com.codemeister.meowreminder.helper.SqliteDatabase;
import com.codemeister.meowreminder.model.Meow;

public class NewCatActivity extends AppCompatActivity {

    private EditText etname, etbreed;
    private ImageButton chooseBtn;
    private Button sendBtn;

    final int REQUEST_CODE_GALLERY = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Add new meow");

        init();

        final SqliteDatabase db = new SqliteDatabase(this);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    // Inserting Meow
                    Log.d("Insert: ", "Inserting ..");
                    db.addMeow(new Meow(etname.getText().toString().trim(),etbreed.getText().toString().trim()));
                    Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


    }

    private void init() {
        etname = (EditText)findViewById(R.id.catname);
        etbreed = (EditText)findViewById(R.id.breed);
        chooseBtn = (ImageButton)findViewById(R.id.imageButton);
        sendBtn = (Button) findViewById(R.id.btnsend);
    }
}
