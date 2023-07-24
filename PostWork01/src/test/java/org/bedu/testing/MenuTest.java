package org.bedu.testing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.bedu.testing.Menu;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private final String exitCommand = "4 \n";
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void addNewInterviewer () {
        final String interviewerName     = "Interviewer Name";
        final String interviewerLastName = "Interviewer Lastname";
        final String interviewerEmail    = "Interviewer Email";
        final String interviewerisActive = "1";
        final String addNewInterviewerCommand = "1 \n "+ interviewerName + " \n " + interviewerLastName + " \n " + interviewerEmail + " \n "+interviewerisActive+" \n";

        provideInput(addNewInterviewerCommand+exitCommand);

        Menu.main(new String[0]);
        final String output = getOutput();

        assertTrue(output.contains(interviewerName));
        assertTrue(output.contains(interviewerLastName));
        assertTrue(output.contains(interviewerEmail));
    }

    @Test
    public void getInterviewer () {
        final String interviewerName     = "Interviewer Name";
        final String interviewerLastName = "Interviewer Lastname";
        final String interviewerEmail    = "interviewer@mail.com";
        final String interviewerisActive = "1";
        final String addNewInterviewerCommand = "1 \n "+ interviewerName + " \n " + interviewerLastName + " \n " + interviewerEmail + " \n "+interviewerisActive+"  \n";
        final String getInterviewerCommand = "2 \n " + interviewerEmail + " \n ";

        provideInput(addNewInterviewerCommand + getInterviewerCommand + exitCommand);

        Menu.main(new String[0]);
        final String output = getOutput();

        printText(output," getInterviewer ");

        assertTrue(output.contains(interviewerName));
        assertTrue(output.contains(interviewerLastName));
        assertTrue(output.contains(interviewerEmail));
    }

    @Test
    public void modifyInterviewer () {
        final String interviewerName     = "Interviewer Name";
        final String interviewerLastName = "Interviewer Lastname";
        final String interviewerEmail    = "interviewer@mail.com";
        final String interviewerisActive = "1";

        final String newinterviewerName     = "Interviewer NEW Name";
        final String newinterviewerLastName = "Interviewer NEW Lastname";
        final String newinterviewerEmail    = "newinterviewer@mail.com";
        final String newinterviewerisActive = "1";

        final String addNewInterviewerCommand = "1 \n " +
                                                interviewerName     + " \n " +
                                                interviewerLastName + " \n " +
                                                interviewerEmail    + " \n " +
                                                interviewerisActive + " \n ";

        final String modifyNewInterviewerArguments =  newinterviewerName     + " \n " +
                                                      newinterviewerLastName + " \n " +
                                                      newinterviewerEmail    + " \n " +
                                                      newinterviewerisActive +"  \n";

         /* se necesita "  " \n " si se pone "\n" falla */
        final String modifyInterviewerCommand = "3 \n " + interviewerEmail + " \n " + modifyNewInterviewerArguments;

        final String getNewInterviewerCommand = "2 \n " + newinterviewerEmail + " \n ";

        provideInput(addNewInterviewerCommand  + modifyInterviewerCommand+ getNewInterviewerCommand+exitCommand);

        Menu.main(new String[0]);
        String output = getOutput();

        provideInput( getNewInterviewerCommand+ exitCommand);
       // Menu.main(new String[0]);
        String outputII = getOutput();

        printText(outputII," modifyInterviewer Salida II");

        assertTrue(outputII.contains(newinterviewerName));
        assertTrue(outputII.contains(newinterviewerLastName));
        assertTrue(outputII.contains(newinterviewerEmail));

    }

    private void printText( String output, String sMsg ){

        System.setIn(systemIn);
        System.setOut(systemOut);

        System.out.println( " "+sMsg+": \n");
        System.out.println( " Salida [\n"+output+"\n]");
    }
}