package com.example.faizan.allconcepts.mvvm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.faizan.allconcepts.R;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_TITLE = "com.example.faizan.mvvm_android_sample.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.faizan.mvvm_android_sample.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.example.faizan.mvvm_android_sample.EXTRA_PRIORITY";
    public static final String EXTRA_ID = "com.example.faizan.mvvm_android_sample.EXTRA_ID";


    private EditText edTitle, edDescription;
    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        edTitle = findViewById(R.id.title_ed);
        edDescription = findViewById(R.id.description_ed);
        numberPicker = findViewById(R.id.numberPicker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("EDIT Note");
            edTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            edDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPicker.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));

        } else {
            setTitle("ADD Note");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void saveNote() {
        String title = edTitle.getText().toString();
        String description = edDescription.getText().toString();
        int priority = numberPicker.getValue();


        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "PLease insert Title and Description", Toast.LENGTH_SHORT).show();
            return;
        }


        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }


        setResult(RESULT_OK, data);
        finish();


    }
}
