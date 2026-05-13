package com.sourabh.libraryX.controller;

import com.sourabh.libraryX.dto.ErrorResponse;
import com.sourabh.libraryX.dto.LibraryBookRequest;
import com.sourabh.libraryX.dto.LibraryBookResponse;
import com.sourabh.libraryX.exception.BookNotFoundException;
import com.sourabh.libraryX.service.LibraryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Tag(name = "Book Management", description = "CRUD operations for managing the library book catalog")
public class LibraryController {

    private final LibraryService libraryService;

    @Operation(summary = "Get all books", description = "Retrieves the complete list of books in the library catalog")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the book list")
    @GetMapping("/books")
    public ResponseEntity<List<LibraryBookResponse>> fetchAllBooks(){
        List<LibraryBookResponse> allBooks = libraryService.getAllBooks();
        return ResponseEntity.ok().body(allBooks);
    }

    @Operation(summary = "Get a book by ID", description = "Retrieves a single book by its unique identifier")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book found"),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/books/{id}")
    public ResponseEntity<LibraryBookResponse> fetchBook(
            @Parameter(description = "UUID of the book to retrieve", required = true)
            @PathVariable String id) {
        LibraryBookResponse book = libraryService.getBook(id);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @Operation(summary = "Add a new book", description = "Creates a new book entry in the library catalog")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Book created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/books")
    public ResponseEntity<LibraryBookResponse> addBook(@Valid @RequestBody LibraryBookRequest libraryBookRequest){
        LibraryBookResponse bookAdded = libraryService.addBook(libraryBookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookAdded);
    }

    @Operation(summary = "Update an existing book", description = "Updates all fields of an existing book identified by its UUID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book updated successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/books/{id}")
    public ResponseEntity<LibraryBookResponse> updateBook(
            @Parameter(description = "UUID of the book to update", required = true)
            @PathVariable String id,
            @Valid @RequestBody LibraryBookRequest request) {
        LibraryBookResponse update = libraryService.updateBook(id,request);
        return ResponseEntity.status(HttpStatus.OK).body(update);
    }

    @Operation(summary = "Delete a book", description = "Removes a book from the library catalog by its UUID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Book deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> removeBook(
            @Parameter(description = "UUID of the book to delete", required = true)
            @PathVariable String id) throws BookNotFoundException {
        libraryService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}

