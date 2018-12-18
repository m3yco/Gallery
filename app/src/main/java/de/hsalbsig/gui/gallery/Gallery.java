package de.hsalbsig.gui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Gallery extends AppCompatActivity {

    private final String SCREENSHOT_DIR = "Screenshots";
    private final String CAMERA_DIR = "Camera";
    private String [] titles = {SCREENSHOT_DIR, CAMERA_DIR};
    private String pathScreenShots =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();
    private String pathCamera =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "Camera";

    private ListView list;
    private Button bnt_auswahl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        list = findViewById(android.R.id.list);
        bnt_auswahl = findViewById(R.id.btn_auswahl);
        ArrayAdapter<String> myList =
                new ArrayAdapter<String>(this, R.layout.list_content, titles);
        list.setAdapter(myList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gallery, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id){
            case R.id.action_a:
                // hier Code für den Button der action_a!
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startImageActivity(View view) {
        // Hier die SubAct mit Verzeichnisinfo starten
        Intent startnext;
        startnext = new Intent(Gallery.this, ShowImage.class);
        // PfadInfo an SubActivity weiterleiten, hier später den tatsächlich ausgewählten Pfad!

        // Bundle enthält die zu übergebenden Informationen
        Bundle infos = new Bundle();
        infos.putStringArray("PATH_INFOS", titles);
        startnext.putExtras(infos);

        //Activity starten
        this.startActivity(startnext);

    }
}
