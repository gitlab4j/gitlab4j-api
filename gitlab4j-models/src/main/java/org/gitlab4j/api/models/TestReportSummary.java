package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.List;

import org.gitlab4j.models.utils.JacksonJson;

public class TestReportSummary implements Serializable {
    private static final long serialVersionUID = 1L;

    private TestReportStats total;
    private List<TestSuite> testSuites;

    public TestReportStats getTotal() {
        return total;
    }

    public void setTotal(TestReportStats total) {
        this.total = total;
    }

    public List<TestSuite> getTestSuites() {
        return testSuites;
    }

    public void setTestSuites(List<TestSuite> testSuites) {
        this.testSuites = testSuites;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
