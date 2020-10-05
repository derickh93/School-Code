/**
 * Assignment #7
 * CSCI 370
 * @author Derick Hansraj
 * 12/4/2019
 */

package edu.qc.seclass;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BuggyClassTestSC1a {

    //These test case provide 100% statement coverage without revealing the fault
    @Test
    public void testCase1() {
        assertEquals(10, BuggyClass.buggyMethod1(40, 4));
    }
    
    @Test
    public void testCase2() {
        assertEquals(0, BuggyClass.buggyMethod1(0, 40));
    }
}
