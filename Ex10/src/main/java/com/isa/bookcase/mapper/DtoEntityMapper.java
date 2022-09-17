package com.isa.bookcase.mapper;

import com.isa.bookcase.dto.BookForm;
import com.isa.bookcase.entities.AuthorEntity;
import com.isa.bookcase.entities.BookEntity;

public class DtoEntityMapper {

    public static BookEntity toBookEntity (BookForm bookForm){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(bookForm.getTitle());
        bookEntity.setAuthor(bookForm.getAuthor());
        bookEntity.setForKids(bookForm.isForKids());
        bookEntity.setPages(bookForm.getPages());
        bookEntity.setCategory(bookForm.getCategory());
        bookEntity.setAuthorEntity(new AuthorEntity(bookForm.getAuthor()));
        return bookEntity;
    }
}
