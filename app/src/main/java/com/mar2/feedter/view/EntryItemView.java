package com.mar2.feedter.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jmart.feedter.R;

import java.util.concurrent.ExecutionException;

import com.mar2.feedter.networkactions.ImageGetFromUrl;

public class EntryItemView extends AppCompatActivity {

    private ImageView titleImage;
    private TextView title;
    private TextView content;
    public EntryItemView() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageGetFromUrl na = new ImageGetFromUrl();

        setContentView(R.layout.activity_entry_item_view);
        titleImage = (ImageView) findViewById(R.id.ImgEntry);
        title = (TextView)findViewById(R.id.LblTitle);
        content = (TextView)findViewById(R.id.LblDescription);
        String titulo = getIntent().getStringExtra("title");
        String contenido = getIntent().getStringExtra("description");
        String imagen = getIntent().getStringExtra("image");
        title.setText(Html.fromHtml(titulo));
        content.setText(Html.fromHtml(contenido));
        na.setUrl(imagen);
        try {
            titleImage.setImageDrawable(na.execute().get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
