package com.isa.bookcase.dto;

import com.isa.bookcase.entities.AuthorEntity;
import com.isa.bookcase.model.Category;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class BookForm {

    private Long id;
    @Size(min = 2, max = 256, message="{bookForm.author.error}")
    private String author;
    @Size(min = 2, max = 256, message="{bookForm.title.error}")
    private String title;
    private Category category;
    @Positive(message = "{bookForm.pages.error}")
    private int pages;
    private boolean forKids;
    private AuthorEntity authorEntity;

    public void setAuthorEntity(AuthorEntity authorEntity) { this.authorEntity = authorEntity; }

    public AuthorEntity getAuthorEntity() { return authorEntity; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public Category getCategory() {
        return category;
    }

    public int getPages() {
        return pages;
    }

    public boolean isForKids() {
        return forKids;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setForKids(boolean forKids) {
        this.forKids = forKids;
    }
}
