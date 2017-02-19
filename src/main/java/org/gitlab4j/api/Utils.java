package org.gitlab4j.api;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.core.Response;

public class Utils {

    /**
     * Creates a File that is unique in the specified directory. If the specified
     * filename exists in the directory, "-#" will be appended to the filename until
     * a unique filename can be created.
     * 
     * @param directory
     * @param filename
     * @return a File that is unique in the specified directory
     * @throws IOException
     */
    public static File createUniqueFile(File directory, String filename) throws IOException {

        File uniqueFile = new File(directory, filename);
        if (uniqueFile.createNewFile()) {
            return (uniqueFile);
        }

        String extension = "";
        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex >= 0) {
            extension = filename.substring(dotIndex);
            filename = filename.substring(0, dotIndex);
        }

        int fileNumber = 0;
        while (!uniqueFile.createNewFile()) {
            fileNumber++;
            String numberedFilename = String.format("%s-%d%s", filename, fileNumber, extension);
            uniqueFile = new File(directory, numberedFilename);
        }

        return (uniqueFile);
    }

    /**
     * Get the filename from the "Content-Disposition" header of a JAX-RS response.
     * 
     * @param response the JAX-RS Response instance  to get the "Content-Disposition" header filename from
     * @return the filename from the "Content-Disposition" header of a JAX-RS response, or null
     * if the "Content-Disposition" header is not present in the response
     */
    public static String getFilenameFromContentDisposition(Response response) {

        String disposition = response.getHeaderString("Content-Disposition");
        if (disposition == null || disposition.trim().length() == 0)
            return (null);

        return (disposition.replaceFirst("(?i)^.*filename=\"([^\"]+)\".*$", "$1"));
    }
}
