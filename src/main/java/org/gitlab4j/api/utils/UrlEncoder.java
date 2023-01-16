package org.gitlab4j.api.utils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class UrlEncoder {

    /**
     * URL encodes a String in compliance with GitLabs special differences.
     *
     * @param s the String to URL encode
     * @return the URL encoded strings
     */
    public static String urlEncode(String s) {
  
        String encoded = URLEncoder.encode(s, StandardCharsets.UTF_8);
        // Since the encode method encodes plus signs as %2B,
        // we can simply replace the encoded spaces with the correct encoding here 
        encoded = encoded.replace("+", "%20");
        encoded = encoded.replace(".", "%2E");
        encoded = encoded.replace("-", "%2D");
        encoded = encoded.replace("_", "%5F");
        return (encoded);
    }
}
