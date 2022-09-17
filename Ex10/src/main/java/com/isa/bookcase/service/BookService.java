package com.isa.bookcase.service;

import com.isa.bookcase.dto.BookForm;
import com.isa.bookcase.entities.AuthorEntity;
import com.isa.bookcase.entities.BookEntity;
import com.isa.bookcase.mapper.DtoEntityMapper;
import com.isa.bookcase.model.Category;
import com.isa.bookcase.repository.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class BookService {

    private Dao<BookEntity> bookDao;
    private Dao<AuthorEntity> authorDao;

    @Autowired
    public BookService(Dao<BookEntity> bookDao,Dao<AuthorEntity> authorDao) {

        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }
    @Transactional
    public Collection<BookEntity> getAllBooks() {
        return bookDao.findAll();
    }
    @Transactional
    public List<Category> getBookCategories() {
        return Arrays.asList(Category.values());
    }

    @Transactional
    public void addBook(BookForm bookForm) {
        bookDao.save(DtoEntityMapper.toBookEntity(bookForm));
    }
}
