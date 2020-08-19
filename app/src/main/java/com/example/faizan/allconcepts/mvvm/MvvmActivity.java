package com.example.faizan.allconcepts.mvvm;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.faizan.allconcepts.R;

import java.util.List;

public class MvvmActivity extends AppCompatActivity {
        public static final int ADD_NOTE_REQUEST = 1;
        public static final int EDIT_NOTE_REQUEST = 2;

    private NoteViewModel noteViewModel;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvm_main);

        // FAB BUTTON
        floatingActionButton = findViewById(R.id.btn_add_note);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MvvmActivity.this, AddEditNoteActivity.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });

        //RECYCLER view
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final NoteAdapter noteAdapter = new NoteAdapter();
        recyclerView.setAdapter(noteAdapter);

        //VIEW MODEL
        // Here we are not creating Instance of view model since each time oncreate is called we will get new inbstance of view model
        // Instead we tell android to create view model instance whenever reauired and and take old view model instance whene ever required
        // and we pass our activity our fragment so that view will respect lifecycle of that activity and fragmnet and
        // vm get destroyed if corresponding activity/freagment is destroyed
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                // update RecyclerView


                 noteAdapter.setNotes(notes);

               // noteAdapter.submitList(notes);
                //Toast.makeText(MainActivity.this, "Onchanged", Toast.LENGTH_SHORT).show();
            }
        });


       // For Swiping Options
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                noteViewModel.delete(noteAdapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MvvmActivity.this, "Note DELETED", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);


        // For EDITING CARD VIEW
        noteAdapter.setOnItemClickLitener(new NoteAdapter.onItemClickLitener() {
            @Override
            public void onItemClick(Note note) {
                Intent intent = new Intent(MvvmActivity.this, AddEditNoteActivity.class);
                intent.putExtra(AddEditNoteActivity.EXTRA_TITLE, note.getTitle());
                intent.putExtra(AddEditNoteActivity.EXTRA_DESCRIPTION, note.getDescription());
                intent.putExtra(AddEditNoteActivity.EXTRA_PRIORITY, note.getPriority());
                intent.putExtra(AddEditNoteActivity.EXTRA_ID, note.getId());
                startActivityForResult(intent, EDIT_NOTE_REQUEST);


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddEditNoteActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddEditNoteActivity.EXTRA_DESCRIPTION);
            int priority = data.getIntExtra(AddEditNoteActivity.EXTRA_PRIORITY, 1);

            Note note = new Note(title, description, priority);
            noteViewModel.insert(note);
            Toast.makeText(this, "NOTE Saved", Toast.LENGTH_SHORT).show();


        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditNoteActivity.EXTRA_ID,-1);
                    if(id == -1){
                        Toast.makeText(this, "NOTE cant Be Updated", Toast.LENGTH_SHORT).show();
                        return;
                    }

            String title = data.getStringExtra(AddEditNoteActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddEditNoteActivity.EXTRA_DESCRIPTION);
            int priority = data.getIntExtra(AddEditNoteActivity.EXTRA_PRIORITY, 1);

            Note note = new Note(title, description, priority);
            // Necessary For UPDATE
            note.setId(id);
            noteViewModel.update(note);
            Toast.makeText(this, "NOTE Updated Successfully ", Toast.LENGTH_SHORT).show();




        } else {
            Toast.makeText(this, "NOTE NOT Saved", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_notes:
                noteViewModel.deleteAllnotes();
                Toast.makeText(this, "ALL notes Deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }
}
