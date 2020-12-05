package com.urise.webapp.util;

import java.time.LocalDate;

public class LocalDateAdapter {

    public LocalDate unmarshal(String str) throws Exception {
        return LocalDate.parse(str);
    }

    public String marshal(LocalDate ld) throws Exception {
        return ld.toString();
    }
}
