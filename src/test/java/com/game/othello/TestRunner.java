package com.game.othello;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
  private static void runTests() {
    Result result = JUnitCore.runClasses(TestBoard.class);
    if (result.getFailures().size() > 0) {
      System.out.println(result.getFailures().get(0).toString());   
      return;
    }
    System.out.println(result.wasSuccessful());    
  }
  
  public static void main() {
    runTests();      
  }
}