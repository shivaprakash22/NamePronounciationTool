package com.wellsfargo.pronounciation.NamePronounciation.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class PronounciationDetails {

    public String name;

    public PronounciationDetails(String name) {
        this.name = name;
    }

    public PronounciationDetails() {
    }

    @Override
    public String toString() {
        return "PronounciationDetails{" +
                "name='" + name + '\'' +
                '}';
    }

    public java.lang.String getName() {
        return name;
    }


    @JsonProperty(value="name")
    public void setName(java.lang.String name) {
        this.name = name;
    }
}