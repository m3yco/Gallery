package de.hsalbsig.gui.gallery;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class GalleryOnTouchListener implements View.OnTouchListener{
    private GestureDetector gdetect;

    public GalleryOnTouchListener(GestureDetector gdetect) {
        this.gdetect = gdetect;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gdetect.onTouchEvent(event);
    }
}
