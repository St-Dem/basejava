package com.urise.webapp.model;

public enum ContactsType {
    PHONE("Тел."),
    SKYPE("Skype") {
        @Override
        public String toHtml0(String value) {
            return getTitle() + ": " + toLink("skype:" + value, value);
        }
    },
    EMAIL("Почта") {
        @Override
        public String toHtml0(String value) {
            return getTitle() + ": " + toLink("mailto:" + value, value);
        }
    },
    LINKEDID("Профиль LinkedId") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    GITHUB("Профиль GitHub") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    STACKOVERFLOW("Профиль StackOverFlow") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    HOMEPAGE("Домашняя страница") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    };


    private final String title;

    ContactsType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    protected String toHtml0(String value) {
        return title + ": " + value;
    }

    public String toHtml(String value) {
        return (value == null) ? "" : toHtml0(value);
    }

    public String toLink(String href) {
        return toLink(href, title);
    }

    public static String toLink(String href, String title) {
        return "<a href='" + href + "'>" + title + "</a>";
    }
}
