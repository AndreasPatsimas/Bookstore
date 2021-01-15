package org.bolosis.library.services;

import lombok.extern.slf4j.Slf4j;
import org.bolosis.library.domain.Book;
import org.bolosis.library.dto.BookDto;
import org.bolosis.library.dto.BookRequestDto;
import org.bolosis.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    private ConversionService conversionService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, ConversionService conversionService) {
        this.bookRepository = bookRepository;
        this.conversionService = conversionService;
    }

    @Override
    public List<BookDto> fetchAvailableBooks() {

        log.info("Fetch available books process begins");

        List<Book> books = bookRepository.findByCopiesGreaterThan(0);

        List<BookDto> bookDtos = books
                .stream()
                .map(book -> conversionService.convert(book, BookDto.class))
                .collect(Collectors.toList());

        log.info("Fetch available books process end");

        return bookDtos;
    }

    @Override
    public BookDto fetchById(Long id) {

        log.info("Fetch book by id: {} process begins", id);

        log.info("Fetch book by id: {} process end", id);

        return null;
    }

    @Override
    public void orderBook(Long id, int copies) {

        log.info("Order books process begins");

        log.info("Order books process end");
    }

    @Override
    public void insertBook(BookRequestDto bookRequestDto) {

        log.info("Insert book process begins");

        log.info("Insert book process end");
    }

    @Override
    public void updateBook(Long id, BookRequestDto bookRequestDto) {

        log.info("Update book by id: {} process begins", id);

        log.info("Update book by id: {} process end", id);
    }

    @Override
    public void deleteBook(Long id) {

        log.info("Delete book by id: {} process begins", id);

        log.info("Delete book by id: {} process end", id);
    }
}
