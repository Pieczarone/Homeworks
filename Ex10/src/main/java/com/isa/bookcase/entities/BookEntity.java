package com.isa.bookcase.entities;

import com.isa.bookcase.model.Category;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = BookEntity.TABLE_NAME)
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class BookEntity {

    public static final String TABLE_NAME = "books";
    public static final String COLUMN_PREFIX = "b_";

    @Id
    @GeneratedValue
    @Column(name = COLUMN_PREFIX + "id")
    private Long id;
    @Column(name = COLUMN_PREFIX + "author", nullable = false)
    private String author;
    @Column(name = COLUMN_PREFIX + "title", nullable = false)
    private String title;
    @Enumerated(EnumType.STRING)
    @Column(name = COLUMN_PREFIX + "category", nullable = false)
    private Category category;
    @Column(name = COLUMN_PREFIX + "pages", nullable = false)
    private int pages;
    @Column(name = COLUMN_PREFIX + "forKids", nullable = false)
    private boolean forKids;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = AuthorEntity.COLUMN_PREFIX + "id", nullable = false)
    private AuthorEntity authorEntity;

}
