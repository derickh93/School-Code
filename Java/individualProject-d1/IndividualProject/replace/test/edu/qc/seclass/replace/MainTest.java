/**
 * Title: individualProject-d1
 * Filename: MainTest.java
 * Date Written: December 12, 2019
 * Due Date: December 13, 2019
 *@author Derick Hansraj
 */
package edu.qc.seclass.replace;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class MainTest {

    private ByteArrayOutputStream outStream;
    private ByteArrayOutputStream errStream;
    private PrintStream outOrig;
    private PrintStream errOrig;
    private Charset charset = StandardCharsets.UTF_8;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        outStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outStream);
        errStream = new ByteArrayOutputStream();
        PrintStream err = new PrintStream(errStream);
        outOrig = System.out;
        errOrig = System.err;
        System.setOut(out);
        System.setErr(err);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(outOrig);
        System.setErr(errOrig);
    }

    // Some utilities

    private File createTmpFile() throws IOException {
        File tmpfile = temporaryFolder.newFile();
        tmpfile.deleteOnExit();
        return tmpfile;
    }

    private File createInputFile1() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!");

        fileWriter.close();
        return file1;
    }

    private File createInputFile2() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice");

        fileWriter.close();
        return file1;
    }

    private File createInputFile3() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123");

        fileWriter.close();
        return file1;
    }

    private String getFileContent(String filename) {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    // Actual test cases

    @Test
    public void mainTest1() throws Exception {
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();
        File inputFile3 = createInputFile3();

        String args[] = {"-i", "Howdy", "Hello", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
        Main.main(args);

        String expected1 = "Hello Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"Hello bill\" again!";
        String expected2 = "Hello Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"Hello Bill\" twice";
        String expected3 = "Hello Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());
        String actual3 = getFileContent(inputFile3.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
        assertEquals("The files differ!", expected3, actual3);

        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
        assertFalse(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));
    }

    @Test
    public void mainTest2() throws Exception {
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-b", "-f", "Bill", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy William,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }

    @Test
    public void mainTest3() throws Exception {
        File inputFile = createInputFile3();

        String args[] = {"-f", "-l", "abc", "ABC", "--", inputFile.getPath()};
        Main.main(args);

        String expected = "Howdy Bill, have you learned your ABC and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: ABC and 123";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    @Test
    public void mainTest4() throws Exception {
        File inputFile = createInputFile3();

        String args[] = {"123", "<numbers removed>", "--", inputFile.getPath()};
        Main.main(args);

        String expected = "Howdy Bill, have you learned your abc and <numbers removed>?\n" +
                "It is important to know your abc and <numbers removed>," +
                "so you should study it\n" +
                "and then repeat with me: abc and <numbers removed>";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    @Test
    public void mainTest5() throws Exception {
        File inputFile = createInputFile2();

        String args1[] = {"-b", "--", "-a", "1", "--", inputFile.getPath()};
        Main.main(args1);
        String args2[] = {"--", "-b", "2", "--", inputFile.getPath()};
        Main.main(args2);

        String expected = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "1) Item 1\n" +
                "2) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertTrue(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    @Test
    //Part of cat part assignment: #10
    public void mainTest6() throws Exception{
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-b", "Bill", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy William,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy William\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }
    
    @Test
    //Part of cat part assignment: #11
    public void mainTest7() throws Exception{
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-b", "John", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files are the same!", expected1, actual1);
        assertEquals("The files are the same!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }
    
    @Test
    //Part of cat part assignment: #12
    public void mainTest8() throws Exception{
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-b", "Bill", "Bill", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files are the same!", expected1, actual1);
        assertEquals("The files are the same!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }
    
    @Test
    //Part of cat part assignment: #13
    public void mainTest9() throws Exception {
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-b", "create", "destroy", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can destroy some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files are the same!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }
    
    @Test
    //Part of cat part assignment: #14
    public void mainTest10() throws Exception{
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-b", "John", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files are the same!", expected1, actual1);
        assertEquals("The files are the same!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }
    
    @Test
    //Part of cat part assignment: #15
    public void mainTest11() throws Exception{
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-b", "Bill", "Bill", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files are the same!", expected1, actual1);
        assertEquals("The files are the same!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }
    
    @Test
    //Part of cat part assignment: #16
    public void mainTest12() throws Exception{
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-f", "Bill", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy William,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);

    }
    
    @Test
    //Part of cat part assignment: #17
    public void mainTest13() throws Exception{
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-f", "Bob", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files are the same!", expected1, actual1);
        assertEquals("The files are the same!", expected2, actual2);

    }
    
    @Test
    //Part of cat part assignment: #18
    public void mainTest14() throws Exception{
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-f", "Bill", "Bill", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files are the same!", expected1, actual1);
        assertEquals("The files are the same!", expected2, actual2);

    }
    
    @Test
    //Part of cat part assignment: #19
    public void mainTest15() throws Exception{
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-f", "create", "destroy", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can destroy some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files are the same!", expected2, actual2);

    }
    
    @Test
    //Part of cat part assignment: #20
    public void mainTest16() throws Exception{
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-f", "John", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files are the same!", expected1, actual1);
        assertEquals("The files are the same!", expected2, actual2);

    }
    
    @Test
    //Part of cat part assignment: #21
    public void mainTest17() throws Exception{
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-f", "Bill", "Bill", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files are the same!", expected1, actual1);
        assertEquals("The files are the same!", expected2, actual2);

    }
    
    @Test
    //Part of cat part assignment: #22
    public void mainTest18() throws Exception{
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-l", "Bill", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy William\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);

    }
    
    @Test
    //Part of cat part assignment: #23
    public void mainTest19() throws Exception{
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-l", "Roger", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files are the same!", expected1, actual1);
        assertEquals("The files are the same!", expected2, actual2);

    }
    
    @Test
    //Part of cat part assignment: #24
    public void mainTest20() throws Exception{
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-l", "Bob", "Bob", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files are the same!", expected1, actual1);
        assertEquals("The files are the same!", expected2, actual2);

    }
    
    @Test
    //Part of cat part assignment: #25
    public void mainTest21() throws Exception{
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-l", "create", "destroy", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can destroy some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files are the same!", expected2, actual2);

    }
    
    @Test
    //Part of cat part assignment: #26
    public void mainTest22() throws Exception{
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-l", "Bob", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files are the same!", expected1, actual1);
        assertEquals("The files are the same!", expected2, actual2);

    }
    
    @Test
    //Part of cat part assignment: #27
    public void mainTest23() throws Exception{
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-l", "Bob", "Bob", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files are the same!", expected1, actual1);
        assertEquals("The files are the same!", expected2, actual2);

    }
    
    @Test
    //Part of cat part assignment: #28
    public void mainTest24() throws Exception{
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-i", "Bill", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy William\" again!";
        String expected2 = "Howdy William,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy William\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);

    }
    
    @Test
    //Part of cat part assignment: #29
    public void mainTest25() throws Exception{
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-i", "Bob", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files are the same!", expected1, actual1);
        assertEquals("The files are the same!", expected2, actual2);

    }
    
    @Test
    //Part of cat part assignment: 30
    public void mainTest26() throws Exception{
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-i", "Bill", "Bill", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files are the same!", expected1, actual1);
        assertEquals("The files are the same!", expected2, actual2);

    }
    
    @Test
    //Part of cat part assignment: #31
    public void mainTest27() throws Exception{
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-i", "create", "destroy", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can destroy some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files are the same!", expected2, actual2);

    }
    
    @Test
    //Part of cat part assignment: #32
    public void mainTest28() throws Exception{
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-i", "Bob", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files are the same!", expected1, actual1);
        assertEquals("The files are the same!", expected2, actual2);

    }
    
    @Test
    //Part of cat part assignment: #33
    public void mainTest29() throws Exception{
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-i", "Bill", "Bill", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files are the same!", expected1, actual1);
        assertEquals("The files are the same!", expected2, actual2);

    }
    
    @Test
    //Part of cat part assignment: #34
    public void mainTest30() throws Exception{
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-f", "-l", "-b", "Bill", "William", "--",inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy William,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy William\" twice";
        
        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files are the same!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
    }
    
    @Test
    //Part of cat part assignment: #35
    public void mainTest31() throws Exception{
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-f", "-b", "Bill", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy William,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files are the same!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
        
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }
    
    @Test
    //Part of cat part assignment: #36
    public void mainTes32() throws Exception{
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-l", "-b", "Bill", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy William\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        
        assertEquals("The files differ!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }
    
    @Test
    //Part of cat part assignment: #37
    public void mainTest33() throws Exception{
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-f", "-i", "-b" ,"Bill", "William", "--",inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy William,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
        
        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files differ!", expected2, actual2);
    }
    
    @Test
    //Part of cat part assignment: #38
    public void mainTest34() throws Exception{
    	 File inputFile1 = createInputFile1();
         File inputFile2 = createInputFile2();

         String args[] = {"-l", "-i", "-b" , "Bill", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
         Main.main(args);

         String expected1 = "Howdy Bill,\n" +
                 "This is a test file for the replace utility\n" +
                 "Let's make sure it has at least a few lines\n" +
                 "so that we can create some interesting test cases...\n" +
                 "And let's say \"howdy bill\" again!";
         String expected2 = "Howdy William,\n" +
                 "This is another test file for the replace utility\n" +
                 "that contains a list:\n" +
                 "-a) Item 1\n" +
                 "-b) Item 2\n" +
                 "...\n" +
                 "and says \"howdy Bill\" twice";

         String actual1 = getFileContent(inputFile1.getPath());
         String actual2 = getFileContent(inputFile2.getPath());

         assertEquals("The files are the same!", expected1, actual1);
         assertEquals("The files differ!", expected2, actual2);
         
         assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
         assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }
    
    @Test
    //Part of cat part assignment: #39
    public void mainTest35() throws Exception{
    	 File inputFile1 = createInputFile1();
         File inputFile2 = createInputFile2();

         String args[] = {"-i", "-b", "Bill", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
         Main.main(args);

         String expected1 = "Howdy Bill,\n" +
                 "This is a test file for the replace utility\n" +
                 "Let's make sure it has at least a few lines\n" +
                 "so that we can create some interesting test cases...\n" +
                 "And let's say \"howdy bill\" again!";
         String expected2 = "Howdy William,\n" +
                 "This is another test file for the replace utility\n" +
                 "that contains a list:\n" +
                 "-a) Item 1\n" +
                 "-b) Item 2\n" +
                 "...\n" +
                 "and says \"howdy Bill\" twice";

         String actual1 = getFileContent(inputFile1.getPath());
         String actual2 = getFileContent(inputFile2.getPath());

         assertEquals("The files are the same!", expected1, actual1);
         assertEquals("The files differ!", expected2, actual2);
         
         assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
         assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }
    
    @Test
    //Part of cat part assignment: #40
    public void mainTest36() throws Exception{
    	 File inputFile1 = createInputFile1();
         File inputFile2 = createInputFile2();

         String args[] = {"-f", "-b", "John", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
         Main.main(args);

         String expected1 = "Howdy Bill,\n" +
                 "This is a test file for the replace utility\n" +
                 "Let's make sure it has at least a few lines\n" +
                 "so that we can create some interesting test cases...\n" +
                 "And let's say \"howdy bill\" again!";
         String expected2 = "Howdy Bill,\n" +
                 "This is another test file for the replace utility\n" +
                 "that contains a list:\n" +
                 "-a) Item 1\n" +
                 "-b) Item 2\n" +
                 "...\n" +
                 "and says \"howdy Bill\" twice";

         String actual1 = getFileContent(inputFile1.getPath());
         String actual2 = getFileContent(inputFile2.getPath());

         assertEquals("The files are the same!", expected1, actual1);
         assertEquals("The files are the same!", expected2, actual2);
         
         assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
         assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }
    
    @Test
    //Part of cat part assignment: #41
    public void mainTest37() throws Exception{
    	 File inputFile1 = createInputFile1();
         File inputFile2 = createInputFile2();

         String args[] = {"-f", "-b", "Bill", "Bill", "--", inputFile1.getPath(), inputFile2.getPath()};
         Main.main(args);

         String expected1 = "Howdy Bill,\n" +
                 "This is a test file for the replace utility\n" +
                 "Let's make sure it has at least a few lines\n" +
                 "so that we can create some interesting test cases...\n" +
                 "And let's say \"howdy bill\" again!";
         String expected2 = "Howdy Bill,\n" +
                 "This is another test file for the replace utility\n" +
                 "that contains a list:\n" +
                 "-a) Item 1\n" +
                 "-b) Item 2\n" +
                 "...\n" +
                 "and says \"howdy Bill\" twice";

         String actual1 = getFileContent(inputFile1.getPath());
         String actual2 = getFileContent(inputFile2.getPath());

         assertEquals("The files are the same!", expected1, actual1);
         assertEquals("The files are the same!", expected2, actual2);
         
         assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
         assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }
    
    @Test
    //Part of cat part assignment: #42
    public void mainTest38() throws Exception{
    	 File inputFile1 = createInputFile1();
         File inputFile2 = createInputFile2();

         String args[] = {"-f", "l","-b", "Bill", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
         Main.main(args);

         String expected1 = "Howdy William,\n" +
                 "This is a test file for the replace utility\n" +
                 "Let's make sure it has at least a few lines\n" +
                 "so that we can create some interesting test cases...\n" +
                 "And let's say \"howdy bill\" again!";
         String expected2 = "Howdy William,\n" +
                 "This is another test file for the replace utility\n" +
                 "that contains a list:\n" +
                 "-a) Item 1\n" +
                 "-b) Item 2\n" +
                 "...\n" +
                 "and says \"howdy William\" twice";

         String actual1 = getFileContent(inputFile1.getPath());
         String actual2 = getFileContent(inputFile2.getPath());

         assertEquals("The files differ!", expected1, actual1);
         assertEquals("The files differ!", expected2, actual2);
         
         assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
         assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }
    
    @Test
    //Part of cat part assignment: #43
    public void mainTest39() throws Exception{
    	 File inputFile1 = createInputFile1();
         File inputFile2 = createInputFile2();

         String args[] = {"-f", "-b", "create", "destroy", "--", inputFile1.getPath(), inputFile2.getPath()};
         Main.main(args);

         String expected1 = "Howdy Bill,\n" +
                 "This is a test file for the replace utility\n" +
                 "Let's make sure it has at least a few lines\n" +
                 "so that we can destroy some interesting test cases...\n" +
                 "And let's say \"howdy bill\" again!";
         String expected2 = "Howdy Bill,\n" +
                 "This is another test file for the replace utility\n" +
                 "that contains a list:\n" +
                 "-a) Item 1\n" +
                 "-b) Item 2\n" +
                 "...\n" +
                 "and says \"howdy Bill\" twice";

         String actual1 = getFileContent(inputFile1.getPath());
         String actual2 = getFileContent(inputFile2.getPath());

         assertEquals("The files differ!", expected1, actual1);
         assertEquals("The files are the same!", expected2, actual2);
         
         assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
         assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }
    
    @Test
    //Part of cat part assignment: #44
    public void mainTest40() throws Exception{
    	 File inputFile1 = createInputFile1();
         File inputFile2 = createInputFile2();

         String args[] = {"-l", "-b", "create", "destroy", "--", inputFile1.getPath(), inputFile2.getPath()};
         Main.main(args);

         String expected1 = "Howdy Bill,\n" +
                 "This is a test file for the replace utility\n" +
                 "Let's make sure it has at least a few lines\n" +
                 "so that we can destroy some interesting test cases...\n" +
                 "And let's say \"howdy bill\" again!";
         String expected2 = "Howdy Bill,\n" +
                 "This is another test file for the replace utility\n" +
                 "that contains a list:\n" +
                 "-a) Item 1\n" +
                 "-b) Item 2\n" +
                 "...\n" +
                 "and says \"howdy Bill\" twice";

         String actual1 = getFileContent(inputFile1.getPath());
         String actual2 = getFileContent(inputFile2.getPath());

         assertEquals("The files differ!", expected1, actual1);
         assertEquals("The files are the same!", expected2, actual2);
         
         assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
         assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }
    
    @Test
    //Part of cat part assignment: #45
    public void mainTest41() throws Exception{
    	 File inputFile1 = createInputFile1();
         File inputFile2 = createInputFile2();

         String args[] = {"-f", "-b", "i","Bill", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
         Main.main(args);

         String expected1 = "Howdy William,\n" +
                 "This is a test file for the replace utility\n" +
                 "Let's make sure it has at least a few lines\n" +
                 "so that we can create some interesting test cases...\n" +
                 "And let's say \"howdy bill\" again!";
         String expected2 = "Howdy William,\n" +
                 "This is another test file for the replace utility\n" +
                 "that contains a list:\n" +
                 "-a) Item 1\n" +
                 "-b) Item 2\n" +
                 "...\n" +
                 "and says \"howdy Bill\" twice";

         String actual1 = getFileContent(inputFile1.getPath());
         String actual2 = getFileContent(inputFile2.getPath());

         assertEquals("The files differ!", expected1, actual1);
         assertEquals("The files differ!", expected2, actual2);
         
         assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
         assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }
    
    @Test
    //Part of cat part assignment: #46
    public void mainTest42() throws Exception{
    	 File inputFile1 = createInputFile1();
         File inputFile2 = createInputFile2();

         String args[] = {"-l", "-b","i", "Bill", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
         Main.main(args);

         String expected1 = "Howdy Bill,\n" +
                 "This is a test file for the replace utility\n" +
                 "Let's make sure it has at least a few lines\n" +
                 "so that we can create some interesting test cases...\n" +
                 "And let's say \"howdy William\" again!";
         String expected2 = "Howdy Bill,\n" +
                 "This is another test file for the replace utility\n" +
                 "that contains a list:\n" +
                 "-a) Item 1\n" +
                 "-b) Item 2\n" +
                 "...\n" +
                 "and says \"howdy William\" twice";

         String actual1 = getFileContent(inputFile1.getPath());
         String actual2 = getFileContent(inputFile2.getPath());

         assertEquals("The files differ!", expected1, actual1);
         assertEquals("The files differ!", expected2, actual2);
         
         assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
         assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }
    
    @Test
    //Part of cat part assignment: #47
    public void mainTest43() throws Exception{
    	 File inputFile1 = createInputFile1();
         File inputFile2 = createInputFile2();

         String args[] = {"-i", "-b", "Bill", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
         Main.main(args);

         String expected1 = "Howdy William,\n" +
                 "This is a test file for the replace utility\n" +
                 "Let's make sure it has at least a few lines\n" +
                 "so that we can create some interesting test cases...\n" +
                 "And let's say \"howdy William\" again!";
         String expected2 = "Howdy William,\n" +
                 "This is another test file for the replace utility\n" +
                 "that contains a list:\n" +
                 "-a) Item 1\n" +
                 "-b) Item 2\n" +
                 "...\n" +
                 "and says \"howdy William\" twice";

         String actual1 = getFileContent(inputFile1.getPath());
         String actual2 = getFileContent(inputFile2.getPath());

         assertEquals("The files differ!", expected1, actual1);
         assertEquals("The files differ!", expected2, actual2);
         
         assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
         assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }
    
    @Test
    //Part of cat part assignment: #48
    public void mainTest44() throws Exception{
    	 File inputFile1 = createInputFile1();
         File inputFile2 = createInputFile2();

         String args[] = {"-f", "-b", "Bob", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
         Main.main(args);

         String expected1 = "Howdy Bill,\n" +
                 "This is a test file for the replace utility\n" +
                 "Let's make sure it has at least a few lines\n" +
                 "so that we can create some interesting test cases...\n" +
                 "And let's say \"howdy bill\" again!";
         String expected2 = "Howdy Bill,\n" +
                 "This is another test file for the replace utility\n" +
                 "that contains a list:\n" +
                 "-a) Item 1\n" +
                 "-b) Item 2\n" +
                 "...\n" +
                 "and says \"howdy Bill\" twice";

         String actual1 = getFileContent(inputFile1.getPath());
         String actual2 = getFileContent(inputFile2.getPath());

         assertEquals("The files are the same!", expected1, actual1);
         assertEquals("The files are the same!", expected2, actual2);
         
         assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
         assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }
    
    @Test
    //Part of cat part assignment: #49
    public void mainTest45() throws Exception{
    	 File inputFile1 = createInputFile1();
         File inputFile2 = createInputFile2();

         String args[] = {"-f", "-b", "Bill", "Bill", "--", inputFile1.getPath(), inputFile2.getPath()};
         Main.main(args);

         String expected1 = "Howdy Bill,\n" +
                 "This is a test file for the replace utility\n" +
                 "Let's make sure it has at least a few lines\n" +
                 "so that we can create some interesting test cases...\n" +
                 "And let's say \"howdy bill\" again!";
         String expected2 = "Howdy Bill,\n" +
                 "This is another test file for the replace utility\n" +
                 "that contains a list:\n" +
                 "-a) Item 1\n" +
                 "-b) Item 2\n" +
                 "...\n" +
                 "and says \"howdy Bill\" twice";

         String actual1 = getFileContent(inputFile1.getPath());
         String actual2 = getFileContent(inputFile2.getPath());

         assertEquals("The files are the same!", expected1, actual1);
         assertEquals("The files are the same!", expected2, actual2);
         
         assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
         assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }
}