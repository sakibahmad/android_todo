package com.example.root.todo1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by root on 27/7/17.
 */

// THE FIRST SCREEN WHERE I USED IMAGEVIEW TO OPEN THE MAIN ACTIVITY

public class First extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);
        this.setTitle("Click to Reminder");
        imageView = (ImageView) findViewById(R.id.select);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),ToDoActivity.class);
                startActivity(intent);
            }
        });
    }
}