package com.example.calculator.client;

import com.example.calculator.client.place.MainPlace;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

/**
 * @author hthiess
 * 
 */
@WithTokenizers({ MainPlace.Tokenizer.class })
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {

}
