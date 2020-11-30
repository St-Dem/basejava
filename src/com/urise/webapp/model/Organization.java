package com.urise.webapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String url;
    private final List<PositionInTime> positionInTime;

    public Organization(String name, String url, List<PositionInTime> positionInTime) {
        Objects.requireNonNull(name, "Organization mast have name");

        this.name = name;
        this.url = url;
        this.positionInTime = positionInTime;
    }

    public static class PositionInTime implements Serializable {
        private static final long serialVersionUID = 1L;

        private LocalDate dateStart;
        private LocalDate dateEnd;
        private String position;
        private String text;

        public PositionInTime(LocalDate dateStart, LocalDate dateEnd, String position, String text) {
            Objects.requireNonNull(dateStart, "You work a certain time");
            Objects.requireNonNull(text, "Please enter someText");

            this.dateStart = dateStart;
            this.dateEnd = dateEnd;
            this.position = position;
            this.text = text;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PositionInTime that = (PositionInTime) o;
            return dateStart.equals(that.dateStart) &&
                    Objects.equals(dateEnd, that.dateEnd) &&
                    Objects.equals(position, that.position) &&
                    text.equals(that.text);
        }

        @Override
        public int hashCode() {
            return Objects.hash(dateStart, dateEnd, position, text);
        }

        @Override
        public String toString() {
            return "PositionInTime{" +
                    "dateStart=" + dateStart +
                    ", dateEnd=" + dateEnd +
                    ", position='" + position + '\'' +
                    ", text='" + text + '\'' +
                    '}';
        }

        public PositionInTime(LocalDate dateStart, LocalDate dateEnd, String text) {
            this(dateStart, dateEnd, "learner", text);
        }
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public List<PositionInTime> getPositionInTime() {
        return positionInTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return name.equals(that.name) &&
                Objects.equals(url, that.url) &&
                positionInTime.equals(that.positionInTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url, positionInTime);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", positionInTime=" + positionInTime +
                '}';
    }
}

