package com.example.calculator.client.view;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * @author hthiess
 * 
 */
public interface MainView extends IsWidget {

    /**
     * @param presenter
     */
    void setPresenter(Presenter presenter);

    /**
     * @param task
     */
    void displayTask(String task);

    /**
     * @param result
     */
    void displayResult(String result);

    /**
     * @param message
     */
    void displayError(String message);

    /**
     * @author hthiess
     * 
     */
    public interface Presenter extends Activity {
        /**
         * Navigate to a new Place in the browser
         * 
         * @param place
         */
        void goTo(Place place);
    }
}
