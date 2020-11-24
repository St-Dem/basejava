package com.urise.webapp.model;

import java.util.List;

public class OrganizationsSectionType extends Section {
    private final List<Organization> organizations;

    public OrganizationsSectionType(List<Organization> work) {
        this.organizations = work;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }
}
