package com.example.root.todo1;

/**
 * Created by root on 27/7/17.
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/*
In this class i had specified listview and with the help of
DatabaseHelper class i will be adding more value in list through dialog box
dynamically
 */

public class CompletedTask extends AppCompatActivity {

    //specifying adapter and listview
    ListViewAdapter listViewAdapter;
    ListView listView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //inflate activity_xml through it
        setContentView(R.layout.activity_main);
        this.setTitle("Completed Task");


        //identify the listview
        listView = (ListView) findViewById(R.id.list1);
        //setting the list item to listview
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
        //position of the mouse click to an item
            public boolean onItemLongClick(AdapterView<?> parent, final View view, int position, long id) {

         // alert dialogbox
                final AlertDialog.Builder dialog = new AlertDialog.Builder(CompletedTask.this);
          //set positive and negative response

                dialog.setMessage("Are you sure? Want to delete the task?");
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseManager db = new DatabaseManager(getApplicationContext()).open();

                        String ids = ((TextView) view.findViewById(R.id.taskId)).getText().toString();
                        db.delete(Long.parseLong(ids));
                        Toast.makeText(CompletedTask.this, " Deleted.", Toast.LENGTH_LONG).
                                show();
                        updateUI();
                        db.close();
                    }
                });
                dialog.setNegativeButton("Cancel", null);
                dialog.create();
                dialog.show();
                return true;
            }

        });
        updateUI();
    }


    //update UI if any changes are there
    private void updateUI() {
        DatabaseManager db = new DatabaseManager(getApplicationContext()).open();
        Cursor cursor = db.fetch(1);
        final ArrayList<Integer> id = new ArrayList<>();
        final ArrayList<String> head = new ArrayList<>();
        ArrayList<String> description = new ArrayList<>();
        ArrayList<String> date = new ArrayList<>();
        ArrayList<Integer> stats = new ArrayList<>();

        //move cursor to next attribute
        while (cursor.moveToNext()) {
            head.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_HEAD)));
            id.add(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)));
            date.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DATE)));
            description.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DESCRIPTION)));

            stats.add(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_STATS)));
        }
        listViewAdapter = new ListViewAdapter(this, id, head, description, date, stats);
        listView.setAdapter(listViewAdapter);
        //close cursor
        cursor.close();
        //close database
        db.close();
    }
}