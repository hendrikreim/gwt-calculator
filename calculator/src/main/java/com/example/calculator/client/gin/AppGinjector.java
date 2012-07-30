package com.example.calculator.client.gin;

import com.example.calculator.client.view.MainView;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.web.bindery.event.shared.EventBus;

/**
 * @author hthiess
 * 
 */
@GinModules({ AppGinModule.class })
public interface AppGinjector extends Ginjector {

    /**
     * @return the main activity
     */
    MainView.Presenter getMainActivity();

    /**
     * @return the main view
     */
    MainView getMainView();

    /**
     * @return the default placeController
     */
    PlaceController getPlaceController();

    /**
     * @return the default eventBus
     */
    EventBus getEventBus();

    /**
     * @return the default activityManager
     */
    ActivityManager getActivityManager();

    /**
     * @return the default placeHistoryHandler
     */
    PlaceHistoryHandler getPlaceHistoryHandler();
}
