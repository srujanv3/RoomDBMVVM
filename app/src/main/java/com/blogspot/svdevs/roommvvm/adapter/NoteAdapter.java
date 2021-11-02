package com.blogspot.svdevs.roommvvm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.blogspot.svdevs.roommvvm.R;
import com.blogspot.svdevs.roommvvm.entity.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private List<Note> noteList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note currentNote = noteList.get(position);
        holder.title.setText(currentNote.getTitle());
        holder.desc.setText(currentNote.getDescription());
        holder.priority.setText(String.valueOf(currentNote.getPriority()));
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public void setNoteList(List<Note> notes){
        this.noteList = notes;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView priority, title, desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            priority = itemView.findViewById(R.id.tv_priority);
            title = itemView.findViewById(R.id.tv_title);
            desc = itemView.findViewById(R.id.tv_description);

        }
    }
}
