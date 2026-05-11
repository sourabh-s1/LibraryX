package com.sourabh.libraryX.controller;

import com.sourabh.libraryX.dto.LibraryBookRequest;
import com.sourabh.libraryX.dto.LibraryBookResponse;
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
    public ResponseEntity<?> fetchBook(@PathVariable String id) throws Exception {
        LibraryBookResponse book = libraryService.getBook(id);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @PostMapping("/books")
    public ResponseEntity<LibraryBookResponse> addBook(@Valid @RequestBody LibraryBookRequest libraryBookRequest){
        LibraryBookResponse bookAdded = libraryService.addBook(libraryBookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookAdded);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<LibraryBookResponse> updateBook(@PathVariable String id,@Valid @RequestBody LibraryBookRequest request) throws Exception {
        LibraryBookResponse update = libraryService.updateBook(id,request);
        return ResponseEntity.status(HttpStatus.OK).body(update);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> removeBook(@PathVariable String id){
        boolean deleted = libraryService.deleteBook(id);
        return deleted ? ResponseEntity.status(HttpStatus.NO_CONTENT).body("Book deleted!") : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found with id :"+id);
    }
}
