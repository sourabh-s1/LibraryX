package com.sourabh.libraryX.controller;

import com.sourabh.libraryX.dto.LibraryBookRequest;
import com.sourabh.libraryX.dto.LibraryBookResponse;
import com.sourabh.libraryX.exception.BookNotFoundException;
import com.sourabh.libraryX.service.LibraryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class LibraryController {

    private final LibraryService libraryService;

    @GetMapping("/books")
    public ResponseEntity<List<LibraryBookResponse>> fetchAllBooks(){
        List<LibraryBookResponse> allBooks = libraryService.getAllBooks();
        return ResponseEntity.ok().body(allBooks);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<LibraryBookResponse> fetchBook(@PathVariable String id) {
        LibraryBookResponse book = libraryService.getBook(id);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @PostMapping("/books")
    public ResponseEntity<LibraryBookResponse> addBook(@Valid @RequestBody LibraryBookRequest libraryBookRequest){
        LibraryBookResponse bookAdded = libraryService.addBook(libraryBookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookAdded);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<LibraryBookResponse> updateBook(@PathVariable String id,@Valid @RequestBody LibraryBookRequest request) {
        LibraryBookResponse update = libraryService.updateBook(id,request);
        return ResponseEntity.status(HttpStatus.OK).body(update);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> removeBook(@PathVariable String id) throws BookNotFoundException {
        libraryService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
