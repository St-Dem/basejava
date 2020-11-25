package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class OrganizationsAbstractSectionType extends AbstractSection {
    private final List<Organization> organizations;

    public OrganizationsAbstractSectionType(List<Organization> work) {
        this.organizations = work;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Organization organization : organizations) {
            builder.append(organization).append(System.lineSeparator());
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationsAbstractSectionType that = (OrganizationsAbstractSectionType) o;
        return Objects.equals(organizations, that.organizations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizations);
    }
}
