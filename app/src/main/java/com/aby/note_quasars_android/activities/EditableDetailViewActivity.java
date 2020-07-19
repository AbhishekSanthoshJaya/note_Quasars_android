package com.aby.note_quasars_android.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.aby.note_quasars_android.R;
import com.aby.note_quasars_android.database.Note;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditableDetailViewActivity extends AppCompatActivity {

    private String NOTE_OBJECT_NAME = "NoteOBJECT";
    private Menu menu;

    private boolean isEditMode = false;

    @BindView(R.id.tvNoteTitleDetail)
    EditText tvNoteTitleDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editable_detail_view);
        ButterKnife.bind(this);

        // setup all views
        initialSetupViewsForDetail();
    }

    private void initialSetupViewsForDetail(){

        Intent intent = getIntent();
        if(intent.getSerializableExtra(NOTE_OBJECT_NAME) != null){
            Note note = (Note) intent.getSerializableExtra(NOTE_OBJECT_NAME);
            String titleTransitionName = intent.getStringExtra("transition_name");
            tvNoteTitleDetail.setTransitionName(titleTransitionName);
            tvNoteTitleDetail.setText(note.getTitle());
            tvNoteTitleDetail.setEnabled(false);
            tvNoteTitleDetail.setBackground(null);
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_note,menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.edit_note && !isEditMode){
            setupForEdit();
        }
        else{
            setupForDetail();

            // save to database here
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupForEdit(){
        tvNoteTitleDetail.setEnabled(true);
        isEditMode = true;

        MenuItem editSaveItem = menu.findItem(R.id.edit_note);
        // set your desired icon here based on a flag if you like
        editSaveItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_baseline_save_24
        ));


    }



    private void setupForDetail(){
        tvNoteTitleDetail.setEnabled(false);
        isEditMode = false;
        // change menu icon back to edit
        MenuItem editSaveItem = menu.findItem(R.id.edit_note);
        // set your desired icon here based on a flag if you like
        editSaveItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_baseline_edit_24
        ));

    }
}