package pl.goooldzik.virtualLibrary.book.http.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.goooldzik.virtualLibrary.book.dto.BookDTO;
import pl.goooldzik.virtualLibrary.book.http.request.BookStoreRequest;
import pl.goooldzik.virtualLibrary.book.http.request.BookUpdateRequest;
import pl.goooldzik.virtualLibrary.book.service.BookService;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDTO> index(@RequestParam(name = "showAll", required = false, defaultValue = "false") Boolean showAll) {
        return this.bookService.index(showAll);
    }

    @GetMapping(path = "{bookId}")
    public BookDTO show(@PathVariable Long bookId) {
        return this.bookService.show(bookId);
    }

    @PostMapping
    public void store(@RequestBody BookStoreRequest request) {
        this.bookService.store(request.getAuthor(), request.getTitle(), request.getAmount());
    }

    @PutMapping(path = "{bookId}")
    public void update(
            @PathVariable("bookId") Long bookId,
            @RequestBody BookUpdateRequest request) {
        this.bookService.update(bookId, request.getAuthor(), request.getTitle(), request.getAmount());
    }

    @DeleteMapping(path = "{bookId}")
    public boolean destroy(@PathVariable Long bookId) {
        return this.bookService.destroy(bookId);
    }

    @PatchMapping(path = "{bookId}")
    public boolean recovery(@PathVariable Long bookId) {
        return this.bookService.recovery(bookId);
    }
}
