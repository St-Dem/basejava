package com.urise.webapp.model;

import com.urise.webapp.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.urise.webapp.util.DateUtil.NOW;
import static com.urise.webapp.util.DateUtil.of;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    public final static String NULL_HOLDER = "";
    public final static Organization EMPTY = new Organization(Link.EMPTY,
            PositionInTime.EMPTY);
    private Link link;
    private List<PositionInTime> positionInTime = new ArrayList<>();


    public Organization() {
    }

    public Organization(Link link, PositionInTime ...positionInTime){
        this.link = link;
        this.positionInTime = Arrays.asList(positionInTime);
    }

    public Organization(Link link, List<PositionInTime> positionInTime){
        this.link = link;
        this.positionInTime = positionInTime;
    }

    public Organization(String name, PositionInTime... positionInTime) {
        this(name, null, Arrays.asList(positionInTime));
    }

    public Organization(String name, String url, PositionInTime... positionInTime) {
        this(name, url, Arrays.asList(positionInTime));
    }

    public Organization(String name, List<PositionInTime> positionInTime) {
        this(name, null, positionInTime);
    }

    public Organization(String name, String url, List<PositionInTime> positionInTime) {
        Objects.requireNonNull(name, "Organization mast have name");
        this.link = new Link(name, url);
        this.positionInTime = positionInTime;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class PositionInTime implements Serializable {
        private static final long serialVersionUID = 1L;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate dateStart;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate dateEnd;
        private String position;
        private String description;
        public final static PositionInTime EMPTY = new PositionInTime(NOW, NOW, "");

        public PositionInTime() {
        }

        public PositionInTime(int startYear, Month startMonth, String position, String description) {
            this(of(startYear, startMonth), NOW, position, description);
        }

        public PositionInTime(int startYear, Month startMonth, int endYear, Month endMonth, String position, String description) {
            this(of(startYear, startMonth), of(endYear, endMonth), position, description);
        }

        public PositionInTime(LocalDate dateStart, LocalDate dateEnd, String position) {
            this(dateStart, dateEnd, position, null);
        }

        public PositionInTime(LocalDate dateStart, LocalDate dateEnd, String position, String description) {
            Objects.requireNonNull(dateStart, "You work a certain time");
            Objects.requireNonNull(dateEnd, "endDate must not be null");
            Objects.requireNonNull(position, "Please enter someText");

            this.dateStart = dateStart;
            this.dateEnd = dateEnd;
            this.position = position;
            this.description = description == null ? NULL_HOLDER : description;
        }

        public LocalDate getDateStart() {
            return dateStart;
        }

        public LocalDate getDateEnd() {
            return dateEnd;
        }

        public String getPosition() {
            return position;
        }

        public String getDescription() {
            return description;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PositionInTime that = (PositionInTime) o;
            return dateStart.equals(that.dateStart) &&
                    Objects.equals(dateEnd, that.dateEnd) &&
                    position.equals(that.position) &&
                    Objects.equals(description, that.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(dateStart, dateEnd, position, description);
        }

        @Override
        public String toString() {
            return "PositionInTime{" +
                    "dateStart=" + dateStart +
                    ", dateEnd=" + dateEnd +
                    ", position='" + position + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Link getLink() {
        return link;
    }

    public List<PositionInTime> getPositionInTime() {
        return positionInTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return link.equals(that.link) &&
                positionInTime.equals(that.positionInTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, positionInTime);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "link=" + link +
                ", positionInTime=" + positionInTime +
                '}';
    }
}

