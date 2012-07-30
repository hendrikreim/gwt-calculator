package com.example.calculator.client.activity;

import javax.inject.Inject;

import com.example.calculator.client.place.MainPlace;
import com.example.calculator.client.service.CalcluateServiceAsync;
import com.example.calculator.client.view.MainView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * @author hthiess
 * 
 */
public class MainActivity extends AbstractActivity implements MainView.Presenter {
    private final CalcluateServiceAsync calculateService;
    private final MainView mainView;
    private final PlaceController placeController;

    /**
     * @param placeController
     * @param mainView
     * @param calculateService
     */
    @Inject
    public MainActivity(PlaceController placeController, MainView mainView, CalcluateServiceAsync calculateService) {
        this.placeController = placeController;
        this.mainView = mainView;
        this.calculateService = calculateService;
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        mainView.setPresenter(this);
        panel.setWidget(mainView.asWidget());

    }

    @Override
    public void goTo(Place place) {
        placeController.goTo(place);
        if (place instanceof MainPlace) {
            String task = ((MainPlace) place).getTask();
            mainView.displayTask(task);
            doCalculateTast(task);
        }
    }

    private void doCalculateTast(String task) {
        calculateService.calculate(task, new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught) {
                mainView.displayError(caught.getMessage());
            }

            @Override
            public void onSuccess(String result) {
                mainView.displayResult(result);
            }
        });
    }
}
