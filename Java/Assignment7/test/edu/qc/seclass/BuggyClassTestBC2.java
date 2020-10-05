/**
 * Assignment #7
 * CSCI 370
 * @author Derick Hansraj
 * 12/4/2019
 */

package edu.qc.seclass;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BuggyClassTestBC2 {

    //This test case provides more than 50% branch coverage and reveals the fault
    @Test
    public void testCase1() {
        assertEquals(3, BuggyClass.buggyMethod2(6, 2));
    }
    
    @Test
    public void testCase2() {
        assertEquals(0, BuggyClass.buggyMethod2(5, 5));
    }
    
    @Test
    public void testCase3() {
        assertEquals(1, BuggyClass.buggyMethod2(0, 0));
    }
}
