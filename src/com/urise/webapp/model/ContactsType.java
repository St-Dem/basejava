package com.urise.webapp.model;

public enum ContactsType {
    PHONE("Тел."),
    SKYPE("Skype"),
    EMAIL("Почта"),
    LINKEDID("Профиль LinkedId"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль StackOverFlow"),
    HOMEPAGE("Домашняя страница");


    private final String title;

    ContactsType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
