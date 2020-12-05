package com.urise.webapp.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSectionType extends AbstractSection implements Serializable {
    private static final long serialVersionUID = 1L;

    private  List<String> items;

    public ListSectionType() {
    }

    public ListSectionType(String... items) {
        this(Arrays.asList(items));
    }

    public ListSectionType(List<String> items) {
        Objects.requireNonNull(items, "items must not be null");
        this.items = items;
    }

    public List<String> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return items.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSectionType that = (ListSectionType) o;

        return items.equals(that.items);

    }

    @Override
    public int hashCode() {
        return items.hashCode();
    }
}