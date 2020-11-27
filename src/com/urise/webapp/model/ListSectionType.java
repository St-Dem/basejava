package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListSectionType extends AbstractSection {
    private final List<String> text;

    public ListSectionType(List<String> text) {
        Objects.requireNonNull(text, "text must not be null");
        this.text = text;
    }

    public List<String> getText() {
        return text;
    }

    public void addVault(String text) {
        this.text.add(text);
    }

    @Override
    public String toString() {
        return "" + text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSectionType that = (ListSectionType) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}
