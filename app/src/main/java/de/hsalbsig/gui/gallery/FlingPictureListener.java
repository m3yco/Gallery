package de.hsalbsig.gui.gallery;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class FlingPictureListener extends
        GestureDetector.SimpleOnGestureListener {
    private ShowImage activity;

    public FlingPictureListener (ShowImage activity) {
        this.activity = activity;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2,
                           float velocityX, float velocityY) {

        float schwellwert = 20;

        if (e1.getX() - e2.getX() > schwellwert) {
            activity.links();
        }
        else if(e2.getX() - e1.getX() > schwellwert) {
            activity.rechts();
        }
        return super.onFling(e1,e2,velocityX,velocityY);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }


}
