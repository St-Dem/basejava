package com.urise.webapp.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                ArrayStorageTest.class,
                SortedArrayStorageTest.class,
                ListStorageTest.class,
                MapStorageIntegerTest.class,
                MapStorageStringTest.class,
                MapStorageResumeTest.class,
                ObjectStreamFileStorageTest.class,
                ObjectStreamPathStorageTest.class,
                XmlPathStorageTest.class,
                DataPathStorageTest.class,
                JsonPathStorageTest.class
        })

public class AllStorageTest {
}

