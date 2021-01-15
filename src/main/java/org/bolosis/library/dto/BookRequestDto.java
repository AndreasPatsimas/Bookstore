package org.bolosis.library.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDto {

    private String tittle;
    private String author;
    private int numberOfPages;
    private LocalDate creationDate;
    private int copies;
}
