package org.gitlab4j.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.Date;

import org.gitlab4j.api.utils.ISO8601;
import org.junit.jupiter.api.Test;

public class TestISO8601 {

    private static final String SPACEY_GITLAB_DATE = "2018-03-12 10:16:46 +0700";
    private static final String ISO8601_GITLAB_DATE ="2018-03-12T10:16:46+0700";
    private static final String SPACEY_GITLAB_UTC_DATE = "2018-03-12 03:16:46 UTC";

    private static final String DATE_ONLY = "2018-03-12";
    private static final String DATE_AT_MIDNIGHT ="2018-03-12T00:00:00Z";

    private static final String SPACEY_GITLAB_DATE_WITH_MSEC = "2018-03-12 10:16:46.123 +0700";
    private static final String ISO8601_GITLAB_DATE_WITH_MSEC ="2018-03-12T10:16:46.123+0700";

    private static final String ISO8601_DATE_OFFSET_COLON = "2018-03-12T10:16:46+07:00";

    private static final String ISO8601_DATE_MSEC = "2018-03-12T10:16:46.123Z";
    private static final String ISO8601_DATE_MSEC_OFFSET_COLON = "2018-03-12T10:16:46.123+00:00";
    private static final String ISO8601_GITLAB_DATE_MSEC = "2018-03-12T03:16:46.123-0700";
    private static final String SPACEY_GITLAB_UTC_DATE_MSEC = "2018-03-12 10:16:46.123 UTC";

    @Test
    public void testGitlabDateParse() throws ParseException {
        Date spaceyDate = ISO8601.toDate(SPACEY_GITLAB_DATE);
        Date gitlabDate = ISO8601.toDate(ISO8601_GITLAB_DATE);
        assertEquals(spaceyDate, gitlabDate);
    }

    @Test
    public void testGitlabUtcDateParse() throws ParseException {
        Date spaceyDate = ISO8601.toDate(SPACEY_GITLAB_UTC_DATE);
        Date gitlabDate = ISO8601.toDate(ISO8601_GITLAB_DATE);
        assertEquals(spaceyDate, gitlabDate);
    }

    @Test
    public void testGitlabMsecUtcDateParse() throws ParseException {
        Date spaceyDate = ISO8601.toDate(SPACEY_GITLAB_UTC_DATE_MSEC);
        Date msecDate = ISO8601.toDate(ISO8601_DATE_MSEC);
        assertEquals(spaceyDate, msecDate);
    }

    @Test
    public void testDateOnlyParse() throws ParseException {
        Date dateOnly = ISO8601.toDate(DATE_ONLY);
        Date dateAtMidnight = ISO8601.toDate(DATE_AT_MIDNIGHT);
        assertEquals(dateOnly, dateAtMidnight);
    }

    @Test
    public void testGitlabMsecDateParse() throws ParseException {
        Date spaceyDate = ISO8601.toDate(SPACEY_GITLAB_DATE_WITH_MSEC);
        Date gitlabDate = ISO8601.toDate(ISO8601_GITLAB_DATE_WITH_MSEC);
        assertEquals(spaceyDate, gitlabDate);
    }

    @Test
    public void testMsecDateParse() throws ParseException {
        Date msecDate = ISO8601.toDate(ISO8601_DATE_MSEC);
        Date gitlabMsecDate = ISO8601.toDate(ISO8601_GITLAB_DATE_MSEC);
        assertEquals(msecDate, gitlabMsecDate);
    }

    @Test
    public void testOffsetColonDateParse() throws ParseException {
        Date gitlabOffsetDate = ISO8601.toDate(ISO8601_DATE_OFFSET_COLON);
        Date gitlabDate = ISO8601.toDate(ISO8601_GITLAB_DATE);
        assertEquals(gitlabDate, gitlabOffsetDate);
    }

    @Test
    public void testOffsetColonMsecDateParse() throws ParseException {
        Date msecDate = ISO8601.toDate(ISO8601_DATE_MSEC);
        Date gitlabMsecDate = ISO8601.toDate(ISO8601_DATE_MSEC_OFFSET_COLON);
        assertEquals(msecDate, gitlabMsecDate);
    }
}
