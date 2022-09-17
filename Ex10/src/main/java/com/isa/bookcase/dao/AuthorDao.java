package com.isa.bookcase.dao;

import com.isa.bookcase.entities.AuthorEntity;
import com.isa.bookcase.repository.Dao;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Objects;

@Repository
public class AuthorDao implements Dao<AuthorEntity> {
    @PersistenceContext
    private EntityManager entityManager;

    public AuthorDao(EntityManager entityManager){
        this.entityManager=entityManager;
    }


    @Override
    public AuthorEntity find(Long id){
        return entityManager.find(AuthorEntity.class, id);
    }

    @Override
    public Collection<AuthorEntity> findAll(){
        return entityManager.createQuery("select author from AuthorEntity author", AuthorEntity.class).getResultList();
    }

    @Override
    public void save(AuthorEntity author){
        entityManager.persist(author);
    }

    @Override
    public AuthorEntity update(AuthorEntity author){
        entityManager.merge(author);
        return author;
    }

    @Override
    public void delete(AuthorEntity author){
        entityManager.remove(Objects.requireNonNull(author,"Author cannot be null"));
    }
}
