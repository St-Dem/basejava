package com.urise.webapp.storage.serialize;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactsType, String> contacts = r.getContacts();

            writeCollection(dos, contacts.entrySet(), writer -> {
                dos.writeUTF(writer.getKey().toString());
                dos.writeUTF(writer.getValue());
            });

            Map<SectionType, AbstractSection> sections = r.getSections();
            writeCollection(dos, sections.entrySet(), writer -> {
                SectionType sectionType = writer.getKey();
                AbstractSection abstractSection = writer.getValue();

                dos.writeUTF(sectionType.toString());
                switch (sectionType) {
                    case PERSONAL, OBJECTIVE -> dos.writeUTF(abstractSection.toString());
                    case ACHIEVEMENT, QUALIFICATIONS -> writeCollection(dos, ((ListSectionType) abstractSection).getItems(), dos::writeUTF);
                    case EXPERIENCE, EDUCATION -> writeCollection(dos, ((OrganizationsSectionType) abstractSection).getOrganizations(), organization -> {
                        dos.writeUTF(organization.getLink().getName());
                        dos.writeUTF(organization.getLink().getUrl());
                        writeCollection(dos, organization.getPositionInTime(), positionInTime -> {
                            dos.writeUTF(getDate(positionInTime.getDateStart()));
                            dos.writeUTF(getDate(positionInTime.getDateEnd()));
                            dos.writeUTF(positionInTime.getPosition());
                            dos.writeUTF(positionInTime.getDescription());
                        });
                    });
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            readCollection(dis, () -> {
                resume.addContacts(ContactsType.valueOf(dis.readUTF()), dis.readUTF());
            });

            readCollection(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSection(sectionType, readSection(dis, sectionType));
            });
            return resume;
        }
    }

    interface Writer<T> {
        void write(T t) throws IOException;
    }

    interface Reader<T> {
        void read() throws IOException;
    }

    interface listReader<T> {
        T read() throws IOException;
    }

    private String getDate(LocalDate localDate) {
        return localDate.getYear() + "-" + (localDate.getMonthValue() < 10 ? "0" + localDate.getMonthValue() : localDate.getMonthValue()) + "-01";
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, Writer<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T element : collection) {
            writer.write(element);
        }
    }

    private <T> AbstractSection readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        return switch (sectionType) {
            case PERSONAL, OBJECTIVE -> new TextSectionType(dis.readUTF());
            case ACHIEVEMENT, QUALIFICATIONS -> new ListSectionType(readList(dis, dis::readUTF));
            case EXPERIENCE, EDUCATION -> new OrganizationsSectionType(
                    readList(dis, () -> (
                                    new Organization(dis.readUTF(), dis.readUTF(),
                                            readList(dis, () -> (
                                                            new Organization.PositionInTime(LocalDate.parse(dis.readUTF()),
                                                                    LocalDate.parse(dis.readUTF()), dis.readUTF(), dis.readUTF())
                                                    )
                                            )
                                    )
                            )
                    )
            );
        };
    }

    private <T> void readCollection(DataInputStream dis, Reader<T> reader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            reader.read();
        }
    }

    private <T> List<T> readList(DataInputStream dis, listReader<T> listReader) throws IOException {
        int size = dis.readInt();
        List<T> arrayList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(listReader.read());
        }
        return arrayList;
    }
}