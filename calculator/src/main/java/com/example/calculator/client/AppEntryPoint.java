package com.example.calculator.client;

import com.example.calculator.client.gin.AppGinjector;
import com.example.calculator.client.place.MainPlace;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * 
 * @author hthiess
 */
public class AppEntryPoint implements EntryPoint {
    private final AppGinjector injector = GWT.create(AppGinjector.class);
    private final Place defaultPlace = new MainPlace("zwölf minus 3");
    private final SimplePanel appWidget = new SimplePanel();

    /**
     * This is the entry point method.
     */
    @Override
    public void onModuleLoad() {
        EventBus eventBus = injector.getEventBus();
        PlaceController placeController = injector.getPlaceController();

        ActivityManager activityManager = injector.getActivityManager();
        activityManager.setDisplay(appWidget);

        PlaceHistoryHandler placeHistoryHandler = injector.getPlaceHistoryHandler();
        placeHistoryHandler.register(placeController, eventBus, defaultPlace);

        RootPanel.get().add(appWidget);

        placeHistoryHandler.handleCurrentHistory();
    }
}
