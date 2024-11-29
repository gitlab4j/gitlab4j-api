package org.gitlab4j.api.utils;

import jakarta.ws.rs.core.Response;

/**
 * This class provides static utility methods used throughout GitLab4J.
 */
public class FileUtils {

    /**
     * Get the filename from the "Content-Disposition" header of a JAX-RS response.
     *
     * @param response the JAX-RS Response instance  to get the "Content-Disposition" header filename from
     * @return the filename from the "Content-Disposition" header of a JAX-RS response, or null
     * if the "Content-Disposition" header is not present in the response
     */
    public static String getFilenameFromContentDisposition(Response response) {

        String disposition = response.getHeaderString("Content-Disposition");
        if (disposition == null || disposition.trim().length() == 0) return (null);

        return (disposition.replaceFirst("(?i)^.*filename=\"([^\"]+)\".*$", "$1"));
    }
}
