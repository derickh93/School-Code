/**
 * Assignment #7
 * CSCI 370
 * @author Derick Hansraj
 * 12/4/2019
 */

package edu.qc.seclass;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class BuggyClassTestSC3 {

    //These test case provide 100% statement coverage and less than 100% branch coverage with revealing the fault
    @Test
    public void testCase1() {
        assertEquals(1, BuggyClass.buggyMethod3(9, 3));
    }
    
    @Test
    public void testCase2() {
        assertEquals(0, BuggyClass.buggyMethod3(0, 40));
    }
}