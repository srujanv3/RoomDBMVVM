package com.blogspot.svdevs.roommvvm.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.blogspot.svdevs.roommvvm.dao.NoteDao;
import com.blogspot.svdevs.roommvvm.database.NoteDatabase;
import com.blogspot.svdevs.roommvvm.entity.Note;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application){
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDao = noteDatabase.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(Note note){new insertNoteTask(noteDao).execute(note);}
    public void update(Note note){new updateNoteTask(noteDao).execute(note);}
    public void delete(Note note){new deleteNoteTask(noteDao).execute(note);}
    public void deleteAllNotes(){new deleteAllNoteTask(noteDao).execute();}

    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }

    private static class insertNoteTask extends AsyncTask<Note, Void,Void>{

        private NoteDao noteDao;
        private insertNoteTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class updateNoteTask extends AsyncTask<Note, Void,Void>{

        private NoteDao noteDao;
        private updateNoteTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class deleteNoteTask extends AsyncTask<Note, Void,Void>{

        private NoteDao noteDao;
        private deleteNoteTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class deleteAllNoteTask extends AsyncTask<Void, Void,Void>{

        private NoteDao noteDao;
        private deleteAllNoteTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }

}
