package com.mar2.feedter.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jmart on 16/05/2016.
 */
public class FilterCreationButton extends View{

    public interface OnToggledListener {
        void OnToggled(FilterCreationButton v, boolean touchOn);
    }

    boolean touchOn;
    boolean mDownTouch = false;
    private OnToggledListener toggledListener;
    int idX = 0; //default
    int idY = 0; //default

    public FilterCreationButton(Context context, int x, int y) {
        super(context);
        idX = x;
        idY = y;
        init();
    }

    public FilterCreationButton(Context context) {
        super(context);
        init();
    }

    public FilterCreationButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FilterCreationButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        touchOn = false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
                MeasureSpec.getSize(heightMeasureSpec));
    }


    @Override
    public boolean performClick() {
        super.performClick();
        return true;
    }

    public void setOnToggledListener(OnToggledListener listener){
        toggledListener = listener;
    }

    public int getIdX(){
        return idX;
    }

    public int getIdY(){
        return idY;
    }


}
