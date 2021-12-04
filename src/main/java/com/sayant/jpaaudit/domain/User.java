package com.sayant.jpaaudit.domain;


import org.hibernate.envers.Audited;

import javax.persistence.Entity;

@Entity
@Audited
public class User extends AditableEntity<Long> {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
