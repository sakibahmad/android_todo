package com.example.root.todo1;

/**
 * Created by root on 27/7/17.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

//IN THIS CLASS I WILL PROVIDE CONTENT IN HELP WHICH IS PLACED IN MENU
public class MainActivity extends AppCompatActivity {

    TextView textView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //inflating the contebnt if heLp XML
        setContentView(R.layout.help);
        //setting title in menu an option with help
        this.setTitle("Help");
       //
        textView = (TextView) findViewById(R.id.help_list);
        //set text to textview
        textView.setText("1. To create a new task click on  add icon in toolbar." + " \n" +
                "2. To delete a task permanently, long press on tasks on CompletedTask  of that task." + "\n" +
                "3. To mark any task complete  long press on task." + " \n" +
                "4. To view completed tasks click on completed tasks button on toolbar." + " \n" +
                "5. To edit any task, short click on that task..");
    }
}