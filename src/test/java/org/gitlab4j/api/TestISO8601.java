package org.gitlab4j.api;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.Date;

import org.gitlab4j.api.utils.ISO8601;
import org.junit.Test;

public class TestISO8601 {

    private static final String SPACEY_GITLAB_DATE = "2018-03-12 10:16:46 +0800";
    private static final String ISO8601_GITLAB_DATE ="2018-03-12T10:16:46+0800";

    @Test
    public void testGitlabDateParse() throws ParseException {
        Date spaceyDate = ISO8601.toDate(SPACEY_GITLAB_DATE);
        Date gitlabDate = ISO8601.toDate(ISO8601_GITLAB_DATE);
        assertEquals(spaceyDate, gitlabDate);        
    }
}
