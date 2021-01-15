package org.bolosis.library.services;

import lombok.extern.slf4j.Slf4j;
import org.bolosis.library.domain.Book;
import org.bolosis.library.dto.BookDto;
import org.bolosis.library.dto.BookRequestDto;
import org.bolosis.library.exceptions.ResourceUnacceptableException;
import org.bolosis.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

        Optional<Book> bookOptional = bookRepository.findById(id);

        BookDto bookDto = bookOptional
                .map(book -> conversionService.convert(book, BookDto.class))
                .orElse(null);

        log.info("Fetch book by id: {} process end", id);

        return bookDto;
    }

    @Override
    public void orderBook(Long id, int copies) {

        log.info("Order books process begins");

        Optional<Book> optionalBook = bookRepository.findById(id);

        optionalBook.ifPresent(book -> {

            int order = book.getCopies() - copies;

            if (order < 0)
                throw new ResourceUnacceptableException("Out of Stock");

            bookRepository.orderBook(id, order);
        });

        optionalBook.orElseThrow(() -> new  ResourceUnacceptableException("Order Error"));

        log.info("Order books process end");
    }

    @Override
    public void insertBook(BookRequestDto bookRequestDto) {

        log.info("Insert book process begins");

        try{

            if (bookRequestDto.getCopies() < 0 || bookRequestDto.getNumberOfPages() < 0)
                throw new ResourceUnacceptableException("Insert Error");

            Book book = conversionService.convert(bookRequestDto, Book.class);

            bookRepository.save(book);
        }
        catch (Exception e){

            throw new ResourceUnacceptableException("Insert Error");
        }

        log.info("Insert book process end");
    }

    @Override
    public void updateBook(Long id, BookRequestDto bookRequestDto) {

        log.info("Update book by id: {} process begins", id);

        try{

            Optional<Book> bookOptional = bookRepository.findById(id);

            if (bookRequestDto.getCopies() < 0 || bookRequestDto.getNumberOfPages() < 0 || !bookOptional.isPresent())
                throw new ResourceUnacceptableException("Update Error");

            Book book = conversionService.convert(bookRequestDto, Book.class);
            book.setId(id);

            bookRepository.save(book);
        }
        catch (Exception e){

            throw new ResourceUnacceptableException("Update Error");
        }

        log.info("Update book by id: {} process end", id);
    }

    @Override
    public void deleteBook(Long id) {

        log.info("Delete book by id: {} process begins", id);

        try{

           bookRepository.deleteById(id);
        }
        catch (Exception e){

            throw new ResourceUnacceptableException("Delete Error");
        }

        log.info("Delete book by id: {} process end", id);
    }
}
