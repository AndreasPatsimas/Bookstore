package org.bolosis.library.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private Long id;
    private String tittle;
    private String author;
    private int numberOfPages;

    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate creationDate;
    private int copies;
}
