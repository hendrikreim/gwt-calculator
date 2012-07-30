package com.example.calculator.server;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.example.calculator.server.util.CalculatorTest;
import com.example.calculator.server.util.TermParserTest;

/**
 * @author hthiess
 * 
 */
@RunWith(Suite.class)
@SuiteClasses({ CalculatorTest.class, TermParserTest.class })
public class ServerTestSuite {

}
