package org.bolosis.library.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tittle", nullable = false)
    private String tittle;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "number_pages", nullable = false)
    private int numberOfPages;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @Column(name = "copies", nullable = false)
    private int copies;
}
