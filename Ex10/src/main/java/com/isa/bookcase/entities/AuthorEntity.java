package com.isa.bookcase.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = AuthorEntity.TABLE_NAME)
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AuthorEntity {

    public static final String TABLE_NAME = "author";
    public static final String COLUMN_PREFIX = "a_";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = COLUMN_PREFIX + "id")
    private Long id;
    @Column(name = COLUMN_PREFIX + "author", nullable = false)
    private String author;
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<BookEntity> books = new ArrayList<>();

    public AuthorEntity(String author) {
        this.author = author;
    }
}
