package com.mohsenmostafavi.entity;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractBaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",unique = true,insertable = true,updatable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
