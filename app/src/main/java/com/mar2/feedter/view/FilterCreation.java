package com.mar2.feedter.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;

import com.example.jmart.feedter.R;
import com.mar2.feedter.controller.FilterCreationController;
import com.mar2.feedter.model.classification.GenericClassificationObject;
import com.mar2.feedter.model.classification.contentTypeObject;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class FilterCreation extends AppCompatActivity {

    GridLayout rejilla;
    ArrayList<GenericClassificationObject> contentTypesArray;
   // FilterCreationButton[] botones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_creation);
        rejilla = (GridLayout) findViewById(R.id.mainLayout);
        FilterCreationController fcc = new FilterCreationController(this.getApplicationContext());
        try {
            //ef.getFeeds();
            contentTypesArray = fcc.getFeeds();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        for(GenericClassificationObject obj : contentTypesArray){
            Button bt = new Button(this.getApplicationContext());
            bt.setText(((contentTypeObject)obj).getTypeOfContent());
            rejilla.addView(bt);
        }
/*
        int numOfCol = 2;
        int numOfRow = (int) Math.ceil((contentTypesArray.size()/2));
        botones = new FilterCreationButton[numOfCol*numOfRow];
        for(int yPos=0; yPos<numOfRow; yPos++){
            for(int xPos=0; xPos<numOfCol; xPos++){
                FilterCreationButton tView = new FilterCreationButton(this, xPos, yPos);
                botones[yPos*numOfCol + xPos] = tView;
                rejilla.addView(tView);
            }
        }
*/




    }
}
