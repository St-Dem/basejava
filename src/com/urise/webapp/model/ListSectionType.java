package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListSectionType extends Section {
    private final List<String> vault;

    public ListSectionType(List<String> vault) {
        Objects.requireNonNull(vault, "text must not be null");
        this.vault = vault;
    }

    public List<String> getText() {
        return vault;
    }

    public void addVault(String text) {
        vault.add(text);
    }

    @Override
    public String toString() {
        return "" + vault;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSectionType that = (ListSectionType) o;
        return Objects.equals(vault, that.vault);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vault);
    }
}
