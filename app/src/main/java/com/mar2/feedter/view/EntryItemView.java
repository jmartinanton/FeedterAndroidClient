package com.mar2.feedter.view;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.webkit.WebView;
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
        Spanned htmlAsSpanned = Html.fromHtml(contenido);
        title.setText(Html.fromHtml(titulo));
        content.setText(htmlAsSpanned);
        na.setUrl(imagen);
        try {
            Drawable imagendra = na.execute().get();

            titleImage.setImageDrawable(imagendra);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
