package com.example.faizan.allconcepts.mvvm;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.faizan.allconcepts.R;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter< NoteAdapter.NoteHolder> {

    private List<Note> notes = new ArrayList<>();
    private onItemClickLitener litener;

//    public NoteAdapter() {
//        super(DIFF_CALLBACK);
//    }

//    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
//        @Override
//        public boolean areItemsTheSame(@NonNull Note note, @NonNull Note t1) {
//            return note.getId() == t1.getId();
//        }
//
//        @Override
//        public boolean areContentsTheSame(@NonNull Note note, @NonNull Note t1) {
//            return note.getTitle().equals(t1.getTitle())
//                    && note.getDescription().equals(t1.getDescription())
//                    && note.getPriority() == t1.getPriority();
//        }
//    } ;

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.note_item, viewGroup, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder noteHolder, int i) {
        Note currentNote = notes.get(i);
       // Note currentNote = getItem(i);
        noteHolder.tv_title.setText(currentNote.getTitle());
        noteHolder.tv_description.setText(currentNote.getDescription());
        noteHolder.tv_priority.setText(String.valueOf(currentNote.getPriority()));


    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int position) {
        return notes.get(position);
        //return getItem(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView tv_title;
        private TextView tv_description;
        private TextView tv_priority;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.txt_title);
            tv_description = itemView.findViewById(R.id.txt_description);
            tv_priority = itemView.findViewById(R.id.text_priority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (litener != null && position != RecyclerView.NO_POSITION) {
                        litener.onItemClick(notes.get(position));
                        //litener.onItemClick(getItem(position));
                    }
                }
            });
        }


    }

    public interface onItemClickLitener {

        void onItemClick(Note note);
    }

    public void setOnItemClickLitener(onItemClickLitener litener) {
        this.litener = litener;
    }
}
