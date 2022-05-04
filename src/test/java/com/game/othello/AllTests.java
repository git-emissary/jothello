package com.game.othello;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

// Combines all test classes in one.
@RunWith(Suite.class)
@SuiteClasses({TestBoard.class})
public class AllTests {}
