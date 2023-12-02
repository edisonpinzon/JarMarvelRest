package com.marvel.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Character {
    @JsonProperty
    int id;
    @JsonProperty
    String name;
    @JsonProperty
    String description;
    @JsonProperty
    String modified;
    @JsonProperty
    String thumbnail;

    public Character(int id, String name, String description, String modified, String thumbnail) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.modified = modified;
        this.thumbnail = thumbnail;
    }
}
