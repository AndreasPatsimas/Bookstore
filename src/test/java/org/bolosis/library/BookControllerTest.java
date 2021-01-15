package org.bolosis.library;

import org.bolosis.library.dto.BookRequestDto;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(properties = {"spring.application.name=BookControllerTest",
        "spring.jmx.default-domain=BookControllerTest"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookControllerTest extends BasicWiremockTest {

    @Test
    public void a_fetchAvailableBooks() throws Exception {
        this.mockMvc.perform(get("/books"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    @Test
    public void b_fetchById() throws Exception {
        this.mockMvc.perform(get("/books/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    @Test
    public void c_orderBook() throws Exception {

        this.mockMvc.perform(put("/books/order/{id}/{copies}", 1L, 1))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Ignore
    @Test
    public void d_insertBook() throws Exception {

        BookRequestDto bookRequestDto = BookRequestDto.builder()
                .tittle("Football Stories")
                .author("Tasos Bolosis")
                .numberOfPages(800)
                .creationDate(LocalDate.now())
                .copies(50)
                .build();

        this.mockMvc.perform(post("/books")
                .content(asJsonString(bookRequestDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void e_updateBook() throws Exception {

        BookRequestDto bookRequestDto = BookRequestDto.builder()
                .tittle("Football Stories")
                .author("Tasos Bolosis")
                .numberOfPages(810)
                .creationDate(LocalDate.now())
                .copies(50)
                .build();

        this.mockMvc.perform(put("/books/{id}", 6L)
                .content(asJsonString(bookRequestDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Ignore
    @Test
    public void f_deleteBook() throws Exception {
        this.mockMvc.perform(
                delete("/books/{id}", 8L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
