package de.hsalbsig.gui.gallery;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowImage extends AppCompatActivity {

    private TextView pfade;
    private ImageView image; // Von Ihnen weiter einzubinden
    private GestureDetector gdetect;
    String[] filepaths;
    int pointerPosition = 0;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pfade = findViewById(R.id.showPath);
        String directory = getIntent().getExtras().getString("SELECTED_DIRECTORY");
        image = findViewById(R.id.gallery);
        //pfade.setText(pfadeStrings[0] + "\n" + pfadeStrings[1] + "\n" +  directory);
        this.gdetect = new GestureDetector(this,new FlingPictureListener(this));
        this.image.setOnTouchListener(new GalleryOnTouchListener(gdetect));
        try {
            filepaths = FileIO.loadImagePathNames(directory);
            bitmap = FileIO.createBitmapFromFile(filepaths[pointerPosition]);
            System.out.println("Path " +
                    filepaths[0]);
            image.setImageBitmap(bitmap);
        } catch (Exception e){
            //pfade.setText("Kein Text vorhanden!");
            image.setImageResource(R.drawable.x);
        }
    }
    public void links(){
        if(pointerPosition > 0){
            this.pointerPosition--;
        }
        else{
            this.pointerPosition = filepaths.length-1;
        }
        try{
            bitmap = FileIO.createBitmapFromFile(filepaths[pointerPosition]);
            image.setImageBitmap(bitmap);
        }
        catch (Exception e){
            image.setImageResource(R.drawable.x);
        }

    }

    public void rechts(){
        if(pointerPosition < filepaths.length-1){
            this.pointerPosition++;
        }
        else{
            this.pointerPosition = 0;
        }
        try{
            bitmap = FileIO.createBitmapFromFile(filepaths[pointerPosition]);
            image.setImageBitmap(bitmap);
        }
        catch (Exception e){
            image.setImageResource(R.drawable.x);
        }
    }
}
