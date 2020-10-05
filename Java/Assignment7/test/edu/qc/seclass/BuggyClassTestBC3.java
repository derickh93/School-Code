/**
 * Assignment #7
 * CSCI 370
 * @author Derick Hansraj
 * 12/4/2019
 */

package edu.qc.seclass;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BuggyClassTestBC3 {
	
    //This test case provides 100% branch coverage and doesn't reveal the fault
    @Test
    public void testCase1() {
        assertEquals(1, BuggyClass.buggyMethod3(5, 0));
    }
    
    @Test
    public void testCase2() {
        assertEquals(0, BuggyClass.buggyMethod3(5, 5));
    }
}
