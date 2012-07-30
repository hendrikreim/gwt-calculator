package com.example.calculator.client.view;

import javax.inject.Inject;

import com.example.calculator.client.place.MainPlace;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author hthiess
 * 
 */
public class MainViewImpl extends Composite implements MainView {
    private static MainViewUiBinder uiBinder = GWT.create(MainViewUiBinder.class);

    interface MainViewUiBinder extends UiBinder<Widget, MainViewImpl> {
    }

    @UiField
    Button button;

    @UiField
    TextArea inputArea, resultArea;

    @UiField
    Label label;

    private Presenter presenter;

    /**
     * 
     */
    @Inject
    public MainViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiHandler("button")
    void onClick(ClickEvent e) {
        presenter.goTo(new MainPlace(inputArea.getText()));
    }

    @UiHandler("inputArea")
    void onKeyDown(KeyDownEvent e) {
        if (e.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
            presenter.goTo(new MainPlace(inputArea.getText()));
        }
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void displayResult(String result) {
        label.setVisible(false);
        resultArea.setText(result);
    }

    @Override
    public void displayError(String message) {
        label.setVisible(true);
        label.setText("Error: " + message);
    }

    @Override
    public void displayTask(String task) {
        inputArea.setText(task);
    }
}
