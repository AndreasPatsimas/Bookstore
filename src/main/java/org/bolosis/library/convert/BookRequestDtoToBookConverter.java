package org.bolosis.library.convert;

import org.bolosis.library.domain.Book;
import org.bolosis.library.dto.BookRequestDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookRequestDtoToBookConverter implements Converter<BookRequestDto, Book> {

    @Override
    public Book convert(BookRequestDto bookRequestDto) {
        return Book.builder()
                .tittle(bookRequestDto.getTittle())
                .author(bookRequestDto.getAuthor())
                .numberOfPages(bookRequestDto.getNumberOfPages())
                .creationDate(bookRequestDto.getCreationDate())
                .copies(bookRequestDto.getCopies())
                .build();
    }
}
