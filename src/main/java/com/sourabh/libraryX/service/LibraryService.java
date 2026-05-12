package com.sourabh.libraryX.service;

import com.sourabh.libraryX.dto.LibraryBookRequest;
import com.sourabh.libraryX.dto.LibraryBookResponse;
import com.sourabh.libraryX.exception.BookNotFoundException;
import com.sourabh.libraryX.model.LibraryBook;
import com.sourabh.libraryX.repository.LibraryRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LibraryService {

    private final LibraryRepository libraryRepository;

    public List<LibraryBookResponse> getAllBooks() {
        List<LibraryBookResponse> books = libraryRepository.findAll().stream().map(
                e -> new LibraryBookResponse(e.getId(),e.getName(),e.getAuthor(),e.getGenre(),e.getPublishYear(),e.getQuantity())
        ).toList();
        return books;
    }

    public LibraryBookResponse getBook(String id) {
        LibraryBook book = libraryRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book Not Found!"));

        return new LibraryBookResponse(
                book.getId(),
                book.getName(),
                book.getAuthor(),
                book.getGenre(),
                book.getPublishYear(),
                book.getQuantity()
        );
    }

    public LibraryBookResponse addBook(LibraryBookRequest libraryBookRequest) {
        LibraryBook libraryBook = new LibraryBook();
        libraryBook.setName(libraryBookRequest.name());
        libraryBook.setGenre(libraryBookRequest.genre());
        libraryBook.setPublishYear(libraryBookRequest.publishYear());
        libraryBook.setAuthor(libraryBookRequest.author());
        libraryBook.setQuantity(libraryBookRequest.quantity());

        return mapDto(libraryRepository.save(libraryBook));
    }

    public LibraryBookResponse updateBook(String id,LibraryBookRequest request) {
        LibraryBook book = libraryRepository.findById(id).orElseThrow(() ->new BookNotFoundException("Book not found with id :"+id));

        book.setName(request.name());
        book.setGenre(request.genre());
        book.setAuthor(request.author());
        book.setPublishYear(request.publishYear());
        book.setQuantity(request.quantity());

        return mapDto(libraryRepository.save(book));
    }

    public void deleteBook(String id) {
        LibraryBook book = libraryRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found with id :"+id));
        libraryRepository.delete(book);
    }

    private LibraryBookResponse mapDto(LibraryBook book){
        return new LibraryBookResponse(
                book.getId(),
                book.getName(),
                book.getAuthor(),
                book.getGenre(),
                book.getPublishYear(),
                book.getQuantity()
        );
    }
}
