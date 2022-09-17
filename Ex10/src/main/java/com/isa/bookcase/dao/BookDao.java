package com.isa.bookcase.dao;

import com.isa.bookcase.entities.BookEntity;
import com.isa.bookcase.repository.Dao;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Objects;

@Repository
public class BookDao implements Dao<BookEntity> {

    @PersistenceContext
    private EntityManager entityManager;

    public BookDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public BookEntity find(Long id) {
        return entityManager.find(BookEntity.class, id);
    }

    @Override
    public Collection<BookEntity> findAll() {
        return entityManager.createQuery("select book from BookEntity book", BookEntity.class).getResultList();
    }

    @Override
    public void save(BookEntity book) {
        entityManager.persist(book);
    }

    @Override
    public BookEntity update(BookEntity book) {
        entityManager.merge(book);
        return book;
    }

    @Override
    public void delete(BookEntity book) {
        entityManager.remove(Objects.requireNonNull(book, "Book cannot be null"));
    }
}
