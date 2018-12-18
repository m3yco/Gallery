package de.hsalbsig.gui.gallery;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowImage extends AppCompatActivity {

    private TextView paths;
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        paths = findViewById(R.id.showPath);
        String [] pathsString = getIntent().getExtras().getStringArray("PATH_INFOS");
        paths.setText(pathsString[0]+"\n"+pathsString[1]);

    }

}
