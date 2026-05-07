package com.sourabh.libraryX.controller;

import com.sourabh.libraryX.dto.LibraryBookResponse;
import com.sourabh.libraryX.service.LibraryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class LibraryController {
    @Autowired
    private final LibraryService libraryService;

    @GetMapping("/books")
    ResponseEntity<List<LibraryBookResponse>> fetchAllBooks(){
        List<LibraryBookResponse> allBooks = libraryService.getAllBooks();
        return ResponseEntity.ok().body(allBooks);
    }
}
