package com.example.calculator.server;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.example.calculator.client.service.CalcluateService;
import com.example.calculator.server.util.Calculator;
import com.example.calculator.server.util.Operator;
import com.example.calculator.server.util.TermParser;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class CalculateServiceImpl extends RemoteServiceServlet implements CalcluateService {

    @Override
    public String calculate(String input) throws IllegalArgumentException {
        StringTokenizer tokenizer = new StringTokenizer(input, "\n");
        String result = "";
        while (tokenizer.hasMoreTokens()) {
            result += calculateLine(tokenizer.nextToken()) + "\n";
        }

        return result;
    }

    /**
     * @param input
     * @return calculation result per line
     * @throws IllegalArgumentException
     */
    private int calculateLine(String input) throws IllegalArgumentException {
        List<Integer> numbers = new ArrayList<Integer>();
        List<Operator> operators = new ArrayList<Operator>();

        StringTokenizer tokenizer = new StringTokenizer(input, " ");
        int count = 0;
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (count % 2 == 0) {
                numbers.add(TermParser.parse(token));
            } else {
                operators.add(Operator.parse(token));
            }
            count++;
        }
        if (count % 2 == 0) {
            throw new IllegalArgumentException("Please type a number, an operator and a number. You need at least a valid number.");
        }
        return Calculator.calculate(numbers, operators);
    }

}
