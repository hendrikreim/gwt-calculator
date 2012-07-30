package com.example.calculator.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author hthiess
 * 
 */
public class MainPlace extends Place {
    private final String task;

    /**
     * @param task
     */
    public MainPlace(String task) {
        this.task = task;
    }

    /**
     * @return input
     */
    public String getTask() {
        return task;
    }

    /**
     * @author hthiess
     * 
     */
    public static class Tokenizer implements PlaceTokenizer<MainPlace> {
        @Override
        public String getToken(MainPlace place) {
            return place.getTask();
        }

        @Override
        public MainPlace getPlace(String token) {
            return new MainPlace(token);
        }
    }
}
