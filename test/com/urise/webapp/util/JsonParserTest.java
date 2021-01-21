package com.urise.webapp.util;

import com.urise.webapp.model.AbstractSection;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.TextSectionType;
import com.urise.webapp.storage.AbstractArrayStorageTest;
import org.junit.Assert;
import org.junit.Test;

public class JsonParserTest {
    @Test
    public void testResume() throws Exception {
        String json = JsonParser.write(AbstractArrayStorageTest.RESUME_2);
        System.out.println(json);
        Resume resume = JsonParser.read(json, Resume.class);
        Assert.assertEquals(AbstractArrayStorageTest.RESUME_2, resume);
    }

    @Test
    public void write() throws Exception {
        AbstractSection section1 = new TextSectionType("Objective1");
        String json = JsonParser.write(section1, AbstractSection.class);
        System.out.println(json);
        AbstractSection section2 = JsonParser.read(json, AbstractSection.class);
        Assert.assertEquals(section1, section2);
    }
}