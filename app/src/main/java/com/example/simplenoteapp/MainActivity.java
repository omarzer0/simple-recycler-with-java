package com.example.simplenoteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Note> notes = new ArrayList<>();
        notes.add(new Note("first", "my first", android.R.color.holo_blue_dark));
        notes.add(new Note("second", "my second", android.R.color.holo_red_dark));
        notes.add(new Note("third", "my third", android.R.color.darker_gray));

        RecyclerView recyclerView = findViewById(R.id.recyclerViewRV);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        NoteAdapter noteAdapter = new NoteAdapter(this, notes);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(noteAdapter);
        noteAdapter.setNoteClickListener(new NoteAdapter.NoteClickListener() {
            @Override
            public void onNoteClick(View view, int position) {
                Toast.makeText(MainActivity.this, "#" + position, Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notes.add(new Note("third", "my third", android.R.color.darker_gray));
                noteAdapter.notifyItemInserted(notes.size() - 1);
            }
        });
    }
}