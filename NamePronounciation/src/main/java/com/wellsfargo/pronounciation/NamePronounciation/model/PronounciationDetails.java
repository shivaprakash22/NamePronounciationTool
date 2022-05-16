package com.wellsfargo.pronounciation.NamePronounciation.model;

import java.util.Objects;

public class PronounciationDetails {

    public String name;

    @Override
    public String toString() {
        return "PronounciationDetails{" +
                "name='" + name + '\'' +
                '}';
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }
}