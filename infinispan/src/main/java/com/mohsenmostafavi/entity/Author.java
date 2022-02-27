package com.mohsenmostafavi.entity;

import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

import java.util.Objects;

public class Author {

    private final String id;
    private final String name;
    private final String surname;

    @ProtoFactory
    public Author(String id, String name, String surname) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.surname = Objects.requireNonNull(surname);
    }

    @ProtoField(1)
    public String getId() {
        return id;
    }

    @ProtoField(number = 2)
    public String getName() {
        return name;
    }
    @ProtoField(number = 3)
    public String getSurname() {
        return surname;
    }
}
