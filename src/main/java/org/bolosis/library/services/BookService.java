package org.bolosis.library.services;

import org.bolosis.library.dto.BookDto;
import org.bolosis.library.dto.BookRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    List<BookDto> fetchAvailableBooks();

    BookDto fetchById(Long id);

    void orderBook(Long id, int copies);

    void insertBook(BookRequestDto bookRequestDto);

    void updateBook(Long id, BookRequestDto bookRequestDto);

    void deleteBook(Long id);
}
