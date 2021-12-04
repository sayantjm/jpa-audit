package com.sayant.jpaaudit.domain;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Audited(withModifiedFlag = true )
@AuditTable(value = "Movie_AUD")
public class Movie extends AditableEntity<Long> {
    private String name;
    
    private Integer year;
    
    @Enumerated(EnumType.STRING)
    private Genre genre;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
