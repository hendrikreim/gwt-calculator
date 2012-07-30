package com.example.calculator.client.gin;

import javax.inject.Singleton;

import com.example.calculator.client.AppActivityMapper;
import com.example.calculator.client.AppPlaceHistoryMapper;
import com.example.calculator.client.activity.MainActivity;
import com.example.calculator.client.view.MainView;
import com.example.calculator.client.view.MainViewImpl;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.inject.Provides;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

/**
 * @author hthiess
 * 
 */
public class AppGinModule extends AbstractGinModule {

    @Override
    protected void configure() {
        bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
        bind(ActivityMapper.class).to(AppActivityMapper.class).in(Singleton.class);

        bind(MainView.Presenter.class).to(MainActivity.class).in(Singleton.class);
        bind(MainView.class).to(MainViewImpl.class).in(Singleton.class);
    }

    @Singleton
    @Provides
    PlaceController getPlaceController(EventBus eventBus) {
        return new PlaceController(eventBus);
    }

    @Singleton
    @Provides
    ActivityManager getActivityManager(ActivityMapper mapper, EventBus eventBus) {
        return new ActivityManager(mapper, eventBus);
    }

    @Singleton
    @Provides
    PlaceHistoryHandler getPlaceHistoryHandler(AppPlaceHistoryMapper appPlaceHistoryMapper) {
        return new PlaceHistoryHandler(appPlaceHistoryMapper);
    }
}
