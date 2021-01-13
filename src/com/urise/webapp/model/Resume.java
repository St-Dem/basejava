package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Comparable<Resume>, Serializable {
    private static final long serialVersionUID = 1L;

    private static final Resume EMPTY = new Resume();
    static {
        EMPTY.addSection(SectionType.PERSONAL, TextSectionType.EMPTY);
        EMPTY.addSection(SectionType.OBJECTIVE, TextSectionType.EMPTY);
        EMPTY.addSection(SectionType.ACHIEVEMENT, ListSectionType.EMPTY);
        EMPTY.addSection(SectionType.QUALIFICATIONS, ListSectionType.EMPTY);
        EMPTY.addSection(SectionType.EDUCATION, OrganizationsSectionType.EMPTY);
        EMPTY.addSection(SectionType.EXPERIENCE, OrganizationsSectionType.EMPTY);
    }
    private String uuid;
    private String fullName;
    private final Map<ContactsType, String> contacts = new EnumMap<>(ContactsType.class);
    private final Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public Resume() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContacts(ContactsType type) {
        return contacts.get(type);
    }

    public Map<ContactsType, String> getContacts() {
        return contacts;
    }

    public Map<SectionType, AbstractSection> getSections() {
        return sections;
    }

    public AbstractSection getSection(SectionType type) {
        return sections.get(type);
    }

    public void addContacts(ContactsType type, String value) {
        contacts.put(type, value);
    }

    public void addSection(SectionType type, AbstractSection section) {
        sections.put(type, section);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid) &&
                fullName.equals(resume.fullName) &&
                Objects.equals(contacts, resume.contacts) &&
                Objects.equals(sections, resume.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, contacts, sections);
    }

    @Override
    public String toString() {
        return uuid + '(' + fullName + ')';
    }

    @Override
    public int compareTo(Resume o) {
        int cmp = fullName.compareTo(o.fullName);
        return cmp != 0 ? cmp : uuid.compareTo(o.uuid);
    }
}
