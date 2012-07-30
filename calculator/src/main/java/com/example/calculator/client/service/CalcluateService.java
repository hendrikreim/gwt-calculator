package com.example.calculator.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 * 
 * @author hthiess
 */
@RemoteServiceRelativePath("calculate")
public interface CalcluateService extends RemoteService {
    /**
     * calculates the given task at the server side
     * 
     * @param task
     * @return result
     * @throws IllegalArgumentException
     */
    String calculate(String task) throws IllegalArgumentException;
}
