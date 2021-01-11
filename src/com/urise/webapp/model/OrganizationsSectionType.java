package com.urise.webapp.model;

import com.urise.webapp.util.DateUtil;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrganizationsSectionType extends AbstractSection implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Organization> organizations;

    public OrganizationsSectionType() {
    }

    public OrganizationsSectionType(List<Organization> work) {
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
        OrganizationsSectionType that = (OrganizationsSectionType) o;
        return Objects.equals(organizations, that.organizations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizations);
    }

    public OrganizationsSectionType empty() {
        List<Organization> organizations = new ArrayList<>();
        organizations.add(new Organization("", "", new Organization.PositionInTime(DateUtil.NOW, DateUtil.NOW, "")));
        return new OrganizationsSectionType(organizations);
    }
    public String toHtml(){
        StringBuilder builder = new StringBuilder();
        for (Organization organization : organizations) {
            builder.append(organization).append("<br/>");
        }
        return builder.toString();
    }
}
