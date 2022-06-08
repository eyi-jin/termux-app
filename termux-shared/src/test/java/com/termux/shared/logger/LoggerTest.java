package com.termux.shared.logger;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoggerTest extends TestCase {

    /**
     * Purpose: Check when throwable is null
     * Input: Logger.getMessageAndStackTraceString (null, null), ("message" null)
     * Expected:
     *      (null, null) = null
     *      ("message" null) = "message"
     */
    @Test
    public void testGetMessageAndStackTraceStringForNullThrowable() {
        assertNull(Logger.getMessageAndStackTraceString(null, null));

        String message = "message";
        String result = Logger.getMessageAndStackTraceString(message, null);
        assertEquals(result, message);
    }

    /**
     * Purpose: Check when throwable is given
     * Input: Logger.getMessageAndStackTraceString (null, Throwable), ("message", Throwable)
     * Expected:
     *      (null, Throwable) = Logger.getStackTraceString(Throwable)
     *      ("message", Throwable) = "message:\n" + Logger.getStackTraceString(throwable)
     */
    @Test
    public void testGetMessageAndStackTraceStringForThrowable() {
        String message = "message";
        Throwable throwable = new Throwable();

        String result = Logger.getMessageAndStackTraceString(null, throwable);
        assertEquals(result, Logger.getStackTraceString(throwable));

        result = Logger.getMessageAndStackTraceString(message, throwable);
        String expected = message + ":\n" + Logger.getStackTraceString(throwable);
        assertEquals(result, expected);
    }

    /**
     * Purpose: Check when string is null
     * Input: Logger.getMessageAndStackTraceString (null, null), (null, Throwable)
     * Expected:
     *      (null, null) = null
     *      (null, Throwable) = Logger.getStackTraceString(Throwable)
     */
    @Test
    public void testGetMessageAndStackTraceStringForNullString() {
        Throwable throwable = new Throwable();

        assertNull(Logger.getMessageAndStackTraceString(null, null));

        String result = Logger.getMessageAndStackTraceString(null, throwable);
        assertEquals(result, Logger.getStackTraceString(throwable));
    }

    /**
     * Purpose: Check when string is given
     * Input: Logger.getMessageAndStackTraceString ("message", null), ("message", Throwable)
     * Expected:
     *      ("message" null) = "message"
     *      ("message", Throwable) = "message:\n" + Logger.getStackTraceString(throwable)
     */
    @Test
    public void testGetMessageAndStackTraceStringForString() {
        String message = "message";
        Throwable throwable = new Throwable();

        String result = Logger.getMessageAndStackTraceString(message, null);
        assertEquals(result, message);

        result = Logger.getMessageAndStackTraceString(message, throwable);
        String expected = message + ":\n" + Logger.getStackTraceString(throwable);
        assertEquals(result, expected);
    }

    /**
     * Purpose: Check when string is given
     * Input: Logger.getMessageAndStackTracesString ("message", null), ("message", List<Throwable>)
     * Expected:
     *      ("message" null) = "message"
     *      ("message", List<Throwable>) = "message" + ":\n" + Logger.getStackTracesString(null, Logger.getStackTracesStringArray(throwableList))
     */
    @Test
    public void testGetMessageAndStackTracesStringForString() {
        List<Throwable> throwableList = new ArrayList<Throwable>(Arrays.asList(new Throwable()));

        String result = Logger.getMessageAndStackTracesString("message", null);
        assertEquals(result, "message");

        result = Logger.getMessageAndStackTracesString("message", throwableList);
        String expected = "message" + ":\n" + Logger.getStackTracesString(null, Logger.getStackTracesStringArray(throwableList));
        assertEquals(result, expected);
    }

    /**
     * Purpose: Check when object is null
     * Input: Logger.getSingleLineLogStringEntry (null, null, null), ("label", null, "-")
     * Expected:
     *      (null, null, null) = "null: null"
     *      ("label", null, "-") = "label: -"
     */
    @Test
    public void testGetSingleLineLogStringEntryForNullObject() {
        String result = Logger.getSingleLineLogStringEntry(null, null, null);
        assertEquals(result, "null: null");

        result = Logger.getSingleLineLogStringEntry("label", null, "-");
        assertEquals(result, "label: -");
    }

    /**
     * Purpose: Check when object is null
     * Input: Logger.getSingleLineLogStringEntry (null, "message", null), ("label", "message", "-")
     * Expected:
     *      (null, null, null) = "null: `message`null"
     *      ("label", null, "-") = "label: `message`-"
     */
    @Test
    public void testGetSingleLineLogStringEntryForObject() {
        String result = Logger.getSingleLineLogStringEntry(null, "message", null);
        assertEquals(result, "null: `message`");

        result = Logger.getSingleLineLogStringEntry("label", "message", "-");
        assertEquals(result, "label: `message`");
    }
}