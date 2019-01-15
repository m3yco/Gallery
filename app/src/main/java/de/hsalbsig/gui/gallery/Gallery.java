package de.hsalbsig.gui.gallery;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Gallery extends AppCompatActivity {

    private final String SCREENSHOT_DIR = "Screenshots";
    private final String CAMERA_DIR = "Camera";
    private final String[] perms = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    private String selectedDirectory="";
    private String [] titles = {SCREENSHOT_DIR, CAMERA_DIR, "Choose other directory..."};

    private String pathScreenShots =
            Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/Screenshots";
    private String pathCamera =
            Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DCIM).getAbsolutePath() + "/Camera";

    private ListView list;
    private Button btn_auswahl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        list = findViewById(android.R.id.list);
        btn_auswahl = findViewById(R.id.btn_auswahl);
        btn_auswahl.setEnabled(false);

        ArrayAdapter<String> myList =
                new ArrayAdapter<String>(this,R.layout.list_content,
                        titles);

        list.setAdapter(myList);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String directory = (String)parent.getItemAtPosition(position);
                switch (directory){
                    case "Screenshots":
                        selectedDirectory = pathScreenShots;
                        btn_auswahl.setEnabled(true);
                        break;
                    case "Camera":
                        selectedDirectory = pathCamera;
                        btn_auswahl.setEnabled(true);
                        break;
                    default:
                        btn_auswahl.setEnabled(false);
                }
            }
        });
        if(checkPermissions() == false) {
            try {
                ActivityCompat.requestPermissions((Activity) Gallery.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
            } catch (Exception e){
                e.printStackTrace();
                throw e;
            }
        }
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startListOnClick(){
        list = findViewById(android.R.id.list);
        String listPath = (String) list.getSelectedItem();
        switch (listPath){
            case "Screenshots":
                selectedDirectory = pathScreenShots;
                break;
            case "Camera":
                selectedDirectory = pathCamera;
                break;
        }
    }

    public void startImageActivity(View view) {
        Intent startnext;
        startnext = new Intent(Gallery.this,
                ShowImage.class);
        // PfadInfo an SubActivity weiterleiten
        // Hier geben Sie spaeter den tatsaechlich ausgewaehlten Pfad
        // an!!
        // Bundle enthaelt die zu uebergebenden Infos ...
        Bundle infos = new Bundle();
        // ... nach put...
        infos.putString("SELECTED_DIRECTORY",selectedDirectory);
        startnext.putExtras(infos);
        // Activity starten mit Uebergabe der Infos ...
        this.startActivity(startnext);
    }

    private boolean checkPermissions() {
        boolean result = true;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int granted = ContextCompat.checkSelfPermission(Gallery.this, Manifest.permission.READ_EXTERNAL_STORAGE);
            if(granted != PackageManager.PERMISSION_GRANTED) {
                result = false;
            }
        }
        return result;
    }
}