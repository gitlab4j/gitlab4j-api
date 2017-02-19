package org.gitlab4j.api;

import java.io.IOException;
import java.io.Reader;

public class TestUtils {

    /**
     * Reads the content of a Reader instance and returns it as a String.
     * 
     * @param reader
     * @return the content of a Reader instance as a String
     * @throws IOException
     */
    public static String getReaderContentAsString(Reader reader) throws IOException {

        int count;
        final char[] buffer = new char[2048];
        final StringBuilder out = new StringBuilder();
        while ((count = reader.read(buffer, 0, buffer.length)) >= 0) {
            out.append(buffer, 0, count);
        }

        return (out.toString());
    }
}