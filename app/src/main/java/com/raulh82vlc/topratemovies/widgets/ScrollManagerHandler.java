/*
 * Copyright (C) ${YEAR} Raul Hernandez Lopez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.raulh82vlc.topratemovies.widgets;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import com.raulh82vlc.topratemovies.models.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * ScrollManagerHandler where the toolbar and fab buttons
 * are hidden or shown
 *
 * Created by Raul Hernandez Lopez on 06/03/2015.
 *
 */
public class ScrollManagerHandler extends RecyclerView.OnScrollListener {

    /* Params declaration */
    private static final int MIN_HIDING_SCROLL = 15;
    private boolean isHidden;
    private int concentratedDyFactor;
    private int finalDyFactor;
    private int offsetFactor;
    private Map<View, Constants.TOWARDS> viewsListToHide;

    /**
     * Constructor ScrollManagerHandler
     *
     * */
    public ScrollManagerHandler()
    {
        viewsListToHide = new HashMap<>();
        isHidden = false;
        concentratedDyFactor = 0;
        finalDyFactor = 0;
        offsetFactor = 0;
    }

    /**
     * Override Method onScrolled
     * this method handles the behaviour
     * whether All elements have to be shown or hidden
     *
     * @param recyclerView View to hide
     * @param iDx the distance in x-axis, not used here
     * @param iDy the distance to move on on y-axis
     *
     */
    @Override
    public void onScrolled(RecyclerView recyclerView, int iDx, int iDy) {
        super.onScrolled(recyclerView, iDx, iDy);
        finalDyFactor += iDy;

        if(finalDyFactor < offsetFactor)
        {
            return;
        }
        if(iDy > 0)
        {
            concentratedDyFactor = concentratedDyFactor > 0 ? concentratedDyFactor + iDy : iDy;
            if(concentratedDyFactor > MIN_HIDING_SCROLL)
            {
                hideAllViews();
            }
        } else if(iDy < 0)
        {
            concentratedDyFactor = concentratedDyFactor < 0 ? concentratedDyFactor + iDy : iDy;
            if(concentratedDyFactor < -MIN_HIDING_SCROLL)
            {
                showAllViews();
            }
        }
    }

    /**
     * Method addViewToHide
     * this method add a view to hide
     *
     * @param iView View to hide
     * @param whichDirection in which direction we hide
     *
     */
    public void addViewToHide(View iView, Constants.TOWARDS whichDirection)
    {
        viewsListToHide.put(iView, whichDirection);
    }

    /**
     * Method hideAllViews
     * this method Hides all views stored in the HashTable
     *
     */
    public void hideAllViews()
    {
        if(!isHidden)
        {
            isHidden = true;
            for(View aView : viewsListToHide.keySet())
            {
                hideOneView(aView, viewsListToHide.get(aView));
            }
        }
    }

    /**
     * Method hideOneView
     * this method hides one view in one direction
     * is calculated the direction with the negative sign
     * and then the animation is started with an interpolator
     * @param iView view to hide
     * @param towards direction to hide
     *
     */
    private void hideOneView(View iView, Constants.TOWARDS towards) {
        int aHeight = computeHowMuchTranslation(iView);
        int aTranslateY = towards == Constants.TOWARDS.TOP ? -aHeight : aHeight;
        startTranslationAnim(iView, aTranslateY, new AccelerateInterpolator(2));
    }

    /**
     * Method startTranslationAnim
     * this method hides one view in one direction
     * @param iView view to hide
     * @param iTranslateY translate to a concrete height
     * @param accelerateInterpolator
     *
     */
    private void startTranslationAnim(View iView, int iTranslateY, Interpolator accelerateInterpolator) {
        Animator aSlideEntersAnim = ObjectAnimator.ofFloat(iView, "translationY", iTranslateY);
        aSlideEntersAnim.setDuration(iView.getContext().getResources().getInteger(android.R.integer.config_mediumAnimTime));
        aSlideEntersAnim.setInterpolator(accelerateInterpolator);
        aSlideEntersAnim.start();
    }

    /**
     * Method computeHowMuchTranslation
     * this method computes the required translation
     * in the Y-axis
     * @param iView view to hide
     * @return height + margins
     *
     */
    private int computeHowMuchTranslation(View iView) {
        int aHeight = iView.getHeight();
        ViewGroup.MarginLayoutParams aParams = (ViewGroup.MarginLayoutParams) iView.getLayoutParams();
        int aMargins = aParams.bottomMargin + aParams.topMargin;
        return aMargins + aHeight;
    }

    /**
     * Method showOneView
     * this method show the previously
     * hidden view
     * @param iView view to show
     *
     */
    private void showOneView(View iView)
    {
        startTranslationAnim(iView, 0, new DecelerateInterpolator(2));
    }

    /**
     * Method showAllViews
     * this method Shows all views stored in the HashTable
     *
     */
    public void showAllViews()
    {
        if(isHidden)
        {
            isHidden = false;
            for(View aView : viewsListToHide.keySet())
            {
                showOneView(aView);
            }
        }
    }

    /**
     * Method setToRecyclerView
     * set the ScrollListener to a preferred RecyclerView
     * @param iRecyclerView
     *
     */
    public void setToRecyclerView(RecyclerView iRecyclerView)
    {
        iRecyclerView.setOnScrollListener(this);
    }

    /**
     * Method setToRecyclerView
     * set the ScrollListener to a preferred RecyclerView
     * @param offsetFactor the offset of the toolbar
     *
     */
    public void setOffsetFactor(int offsetFactor) {
        this.offsetFactor = offsetFactor;
    }
}
