package com.blogspot.svdevs.roommvvm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.blogspot.svdevs.roommvvm.adapter.NoteAdapter;
import com.blogspot.svdevs.roommvvm.entity.Note;
import com.blogspot.svdevs.roommvvm.viewmodel.NoteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int ADD_NOTE_REQ = 1;

    private NoteViewModel noteViewModel;
    private RecyclerView rView;
    private NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton faButton = findViewById(R.id.fab);
        faButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddNoteActivity.class);
                startActivityForResult(intent, ADD_NOTE_REQ);
            }
        });

        rView = findViewById(R.id.recyclerView);
        initRecyclerView();

        noteViewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(NoteViewModel.class);
        noteViewModel.getAllnotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                // update the data to recycler view
                adapter.setNoteList(notes);
                Toast.makeText(MainActivity.this, "Welcome !!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initRecyclerView() {
        adapter = new NoteAdapter();
        rView.setLayoutManager(new LinearLayoutManager(this));
        rView.setHasFixedSize(true);
        rView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ADD_NOTE_REQ && resultCode==RESULT_OK){
            String title = data.getStringExtra(AddNoteActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION);
            int priority = data.getIntExtra(AddNoteActivity.EXTRA_PRIORITY,1);

            Note note = new Note(title, description,priority);
            noteViewModel.insert(note);
            Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "return...", Toast.LENGTH_SHORT).show();
        }
    }
}