package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;
import org.gitlab4j.models.utils.JacksonJsonEnumHelper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class LineRange implements Serializable {
    private static final long serialVersionUID = 1L;

    public static class Position implements Serializable {
        private static final long serialVersionUID = 1L;

        public static enum PositionType {
            OLD,
            NEW;
            private static JacksonJsonEnumHelper<PositionType> enumHelper =
                    new JacksonJsonEnumHelper<>(PositionType.class, false, false);

            @JsonCreator
            public static PositionType forValue(String value) {
                return enumHelper.forValue(value);
            }

            @JsonValue
            public String toValue() {
                return (enumHelper.toString(this));
            }

            @Override
            public String toString() {
                return (enumHelper.toString(this));
            }
        }

        private String lineCode;
        private PositionType type;
        private Integer oldLine;
        private Integer newLine;

        public String getLineCode() {
            return lineCode;
        }

        public void setLineCode(String lineCode) {
            this.lineCode = lineCode;
        }

        public Position withLineCode(String lineCode) {
            this.lineCode = lineCode;
            return this;
        }

        public PositionType getType() {
            return type;
        }

        public void setType(PositionType type) {
            this.type = type;
        }

        public Position withType(PositionType type) {
            this.type = type;
            return this;
        }

        public Integer getOldLine() {
            return oldLine;
        }

        public void setOldLine(Integer oldLine) {
            this.oldLine = oldLine;
        }

        public Position withOldLine(Integer oldLine) {
            this.oldLine = oldLine;
            return this;
        }

        public Integer getNewLine() {
            return newLine;
        }

        public void setNewLine(Integer newLine) {
            this.newLine = newLine;
        }

        public Position withNewLine(Integer newLine) {
            this.newLine = newLine;
            return this;
        }

        @Override
        public String toString() {
            return (JacksonJson.toJsonString(this));
        }
    }

    private Position start;
    private Position end;

    public Position getStart() {
        return start;
    }

    public void setStart(Position start) {
        this.start = start;
    }

    public LineRange withStart(Position start) {
        this.start = start;
        return this;
    }

    public Position getEnd() {
        return end;
    }

    public void setEnd(Position end) {
        this.end = end;
    }

    public LineRange withEnd(Position end) {
        this.end = end;
        return this;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
