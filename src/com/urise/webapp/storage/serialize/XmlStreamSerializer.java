package com.urise.webapp.storage.serialize;

import com.urise.webapp.model.*;
import com.urise.webapp.util.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class XmlStreamSerializer implements StreamSerializer{
    private XmlParser xmlParser;

    public XmlStreamSerializer() {
        xmlParser = new XmlParser(
                Resume.class, Organization.class,
                OrganizationsSectionType.class, TextSectionType.class, ListSectionType.class, Organization.PositionInTime.class);
    }

    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            xmlParser.marshall(r, w);
        }
    }

    public Resume doRead(InputStream is) throws IOException {
        try (Reader r = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return xmlParser.unmarshall(r);
        }
    }
}
