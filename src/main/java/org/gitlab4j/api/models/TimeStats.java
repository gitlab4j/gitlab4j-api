package org.gitlab4j.api.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TimeStats {

    private Integer timeEstimate;
    private Integer totalTimeSpent;
    private Duration humanTimeEstimate;
    private Duration humanTotalTimeSpent;

    public Integer getTimeEstimate() {
        return timeEstimate;
    }

    public void setTimeEstimate(Integer timeEstimate) {
        this.timeEstimate = timeEstimate;
    }

    public Integer getTotalTimeSpent() {
        return totalTimeSpent;
    }

    public void setTotalTimeSpent(Integer totalTimeSpent) {
        this.totalTimeSpent = totalTimeSpent;
    }

    public Duration getHumanTimeEstimate() {
        return humanTimeEstimate;
    }

    public void setHumanTimeEstimate(Duration humanTimeEstimate) {
        this.humanTimeEstimate = humanTimeEstimate;
    }

    public Duration getHumanTotalTimeSpent() {
        return humanTotalTimeSpent;
    }

    public void setHumanTotalTimeSpent(Duration humanTotalTimeSpent) {
        this.humanTotalTimeSpent = humanTotalTimeSpent;
    }
}
