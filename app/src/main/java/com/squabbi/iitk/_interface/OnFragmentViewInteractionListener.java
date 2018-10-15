package com.squabbi.iitk._interface;

import android.view.View;

/**
 * A public interface for listening for view interactions from fragments. i.e. button presses
 * and other visual interactions.
 */
public interface OnFragmentViewInteractionListener {

    /**
     * Returns the view that called the OnClick.
     * @param view the View that initiated the OnClick call.
     */
    void onViewPressed(View view);
}
