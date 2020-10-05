/**
 * Assignment #7
 * CSCI 370
 * @author Derick Hansraj
 * 12/4/2019
 */

package edu.qc.seclass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BuggyClassTestSC1b {

    //This test case provides less than 50% statement coverage and reveals the fault
    @Test
    public void testCase1() {
        assertEquals(2, BuggyClass.buggyMethod1(6, 3));
    }
    
    @Test
    public void testCase2() {
        assertEquals(0, BuggyClass.buggyMethod1(0, 0));
    }

}