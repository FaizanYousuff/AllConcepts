package com.example.faizan.allconcepts.mvvm;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("SElect * FROM note_table ORder by priority Desc")
    // since we are using Live data as soon as data is changed inside this column our Activity will be notified via OBserver
    LiveData<List<Note>> getAllNotes();
}
