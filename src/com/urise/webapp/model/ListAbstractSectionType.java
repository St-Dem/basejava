package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListAbstractSectionType extends AbstractSection {
    private final List<String> vault;

    public ListAbstractSectionType(List<String> vault) {
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
        ListAbstractSectionType that = (ListAbstractSectionType) o;
        return Objects.equals(vault, that.vault);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vault);
    }
}
