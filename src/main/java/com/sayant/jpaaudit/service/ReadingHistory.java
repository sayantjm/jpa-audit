package com.sayant.jpaaudit.service;

import com.sayant.jpaaudit.domain.Movie;
import com.sayant.jpaaudit.domain.User;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juanma Perales on 4/12/21
 */
@Service
public class ReadingHistory {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private final EntityManager entityManager;

    @Autowired
    public ReadingHistory(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<User> getUserHistoricalChanges() {
        AuditReader reader = AuditReaderFactory.get(entityManager);
        AuditQuery query = reader.createQuery()
                .forRevisionsOfEntity(User.class, false, true);

        List<User> resultList = query.getResultList();
        return resultList;
    }

    public List<Movie> getMovieHistoricalChanges() {
        AuditReader reader = AuditReaderFactory.get(entityManager);
        AuditQuery query = reader.createQuery()
                .forRevisionsOfEntity(Movie.class, true, true);

        List<Movie> resultList = query.getResultList();
        return resultList;
    }

    public List<Movie> getLastMovieChanges() {
        AuditReader reader = AuditReaderFactory.get(entityManager);
        AuditQuery query = reader.createQuery()
                .forRevisionsOfEntity(Movie.class, true, true)
                //.add(AuditEntity.property("genre").hasChanged())
                .add(AuditEntity.property("name").hasChanged());

        return query.getResultList();
    }

}
