package org.bolosis.library.controllers;

import lombok.extern.slf4j.Slf4j;
import org.bolosis.library.dto.BookDto;
import org.bolosis.library.dto.BookRequestDto;
import org.bolosis.library.exceptions.ErrorResponse;
import org.bolosis.library.exceptions.ResourceUnacceptableException;
import org.bolosis.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping(value = "/books")
@RestController
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<BookDto> fetchAvailableBooks() {
        log.info("Fetch available books");
        return bookService.fetchAvailableBooks();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    BookDto fetchById(@PathVariable Long id) {
        log.info("Fetch book by id: {}", id);
        return bookService.fetchById(id);
    }

    @PutMapping(value = "/order/{id}/{copies}")
    ResponseEntity orderBook(@PathVariable("id") Long id,
                             @PathVariable("copies") int copies){

        log.info("Order books");
        bookService.orderBook(id, copies);
        return ResponseEntity.ok("Successful Order");
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> insertBook(@RequestBody BookRequestDto bookRequestDto){

        log.info("Insert book {}", bookRequestDto);
        bookService.insertBook(bookRequestDto);
        return ResponseEntity.ok("Insert completed");
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> updateBook(@PathVariable("id") Long id,
                                        @RequestBody BookRequestDto bookRequestDto){

        log.info("Update book {}", bookRequestDto);
        bookService.updateBook(id, bookRequestDto);
        return ResponseEntity.ok("Update completed");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id){

        log.info("Delete book by id: {}", id);
        bookService.deleteBook(id);
        return ResponseEntity.ok("Delete completed");
    }

    @ExceptionHandler(ResourceUnacceptableException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(HttpStatus.NOT_ACCEPTABLE.value())
                .status(HttpStatus.NOT_ACCEPTABLE)
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }
}
