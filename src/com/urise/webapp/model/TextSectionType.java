package com.urise.webapp.model;

import java.io.Serializable;
import java.util.Objects;

public class TextSectionType extends AbstractSection implements Serializable {
    private static final long serialVersionUID = 1L;
    public static TextSectionType EMPTY = new TextSectionType("");
    private String text;

    public TextSectionType() {
    }

    public TextSectionType(String text) {
        Objects.requireNonNull(text, "text must not be null");
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextSectionType that = (TextSectionType) o;

        return text.equals(that.text);

    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }
}