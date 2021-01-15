package org.bolosis.library.controllers;

import lombok.extern.slf4j.Slf4j;
import org.bolosis.library.dto.BookDto;
import org.bolosis.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
}
