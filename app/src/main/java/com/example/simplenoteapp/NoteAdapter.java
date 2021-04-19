package com.example.simplenoteapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private List<Note> mNotes;
    private Context context;
    private NoteClickListener noteClickListener;

    public NoteAdapter(Context context, List<Note> notes) {
        this.mNotes = notes;
        this.context = context;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = mNotes.get(position);
        holder.noteImageImg.setImageResource(note.getColor());
        holder.titleTV.setText(note.getTitle());
        holder.descriptionTV.setText(note.getDescription());
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // define your views
        private ImageView noteImageImg;
        private TextView titleTV, descriptionTV;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            // init the views
            noteImageImg = itemView.findViewById(R.id.noteImageImg);
            titleTV = itemView.findViewById(R.id.titleTV);
            descriptionTV = itemView.findViewById(R.id.descriptionTV);
            // set listener for each item
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (noteClickListener != null) {
                noteClickListener.onNoteClick(v, getAdapterPosition());
            }
        }
    }

    void setNoteClickListener(NoteClickListener listener) {
        this.noteClickListener = listener;
    }


    public interface NoteClickListener {
        void onNoteClick(View view, int position);
    }
}
