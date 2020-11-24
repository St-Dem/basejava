package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {
    private String name;
    private String url;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private String position;
    private String text;

    public Organization(String name, String url, LocalDate dateStart, LocalDate dateEnd, String position, String text) {
        Objects.requireNonNull(name, "Organization mast have name");
        Objects.requireNonNull(dateStart, "You work a certain time");
        Objects.requireNonNull(text, "Please enter someText");

        this.name = name;
        this.url = url;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.position = position;
        this.text = text;
    }

    public Organization(String name, String url, LocalDate dateStart, LocalDate dateEnd, String text) {
        this(name, url, dateStart, dateEnd, "apprentice", text);
    }

    public Organization(String name, String url, LocalDate dateStart, String position, String text) {
        this(name, url, dateStart, LocalDate.now(), position, text);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
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

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(url, that.url) &&
                Objects.equals(dateStart, that.dateStart) &&
                Objects.equals(dateEnd, that.dateEnd) &&
                Objects.equals(position, that.position) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url, dateStart, dateEnd, position, text);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", position='" + position + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}

