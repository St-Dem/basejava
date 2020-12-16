package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Config {
    private static final Config INSTANCE = new Config();
    private static final File PROPS = new File(".config/resume.properties");

    public Config getInstance() {
        return INSTANCE;
    }

    private Config() {
        try (InputStream is = new FileInputStream(PROPS)) {


        } catch (IOException e) {
          throw  new IllegalStateException("pnvalid config file" + PROPS.getAbsolutePath());
        }

    }
}
