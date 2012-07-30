package com.example.calculator.client;

import javax.inject.Inject;

import com.example.calculator.client.gin.AppGinjector;
import com.example.calculator.client.place.MainPlace;
import com.example.calculator.client.view.MainView.Presenter;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

/**
 * @author hthiess
 * 
 */
public class AppActivityMapper implements ActivityMapper {
    private final AppGinjector ginjector;

    /**
     * @param ginjector
     */
    @Inject
    public AppActivityMapper(AppGinjector ginjector) {
        this.ginjector = ginjector;
    }

    @Override
    public Activity getActivity(Place place) {
        if (place instanceof MainPlace) {
            Presenter mainActivity = ginjector.getMainActivity();
            mainActivity.goTo(place);
            return mainActivity;
        }
        return null;
    }
}
