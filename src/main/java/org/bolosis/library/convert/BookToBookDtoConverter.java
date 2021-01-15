package org.bolosis.library.convert;

import org.bolosis.library.domain.Book;
import org.bolosis.library.dto.BookDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookToBookDtoConverter implements Converter<Book, BookDto> {

    @Override
    public BookDto convert(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .tittle(book.getTittle())
                .author(book.getAuthor())
                .numberOfPages(book.getNumberOfPages())
                .creationDate(book.getCreationDate())
                .copies(book.getCopies())
                .build();
    }
}
