package com.sourabh.libraryX.service;

import com.sourabh.libraryX.dto.LibraryBookRequest;
import com.sourabh.libraryX.dto.LibraryBookResponse;
import com.sourabh.libraryX.model.LibraryBook;
import com.sourabh.libraryX.repository.LibraryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public LibraryBookResponse getBook(String id) throws Exception {
        LibraryBook book = libraryRepository.findById(id).orElseThrow(() -> new Exception("Book not found!"));

        return new LibraryBookResponse(
                book.getId(),
                book.getName(),
                book.getAuthor(),
                book.getGenre(),
                book.getPublishYear(),
                book.getQuantity()
        );
    }

    public boolean addBook(LibraryBookRequest libraryBookRequest) {
        LibraryBook libraryBook = new LibraryBook();
        libraryBook.setName(libraryBookRequest.name());
        libraryBook.setGenre(libraryBookRequest.genre());
        libraryBook.setPublishYear(libraryBookRequest.publishYear());
        libraryBook.setAuthor(libraryBookRequest.author());
        libraryBook.setQuantity(libraryBookRequest.quantity());

        LibraryBook rs = libraryRepository.save(libraryBook);
        return true;
    }

    public boolean updateBook(String id,LibraryBookRequest request) {
        boolean doesExist = libraryRepository.existsById(id);
        if(!doesExist){
            return false;
        }

        LibraryBook updatedBook = new LibraryBook(
                id,
                request.name(),
                request.author(),
                request.genre(),
                request.publishYear(),
                request.quantity()
        );
        libraryRepository.save(updatedBook);

        return true;
    }

    public boolean deleteBook(String id) {
        boolean existsById = libraryRepository.existsById(id);
        if(!existsById){
            return false;
        }
        libraryRepository.deleteById(id);
        return true;
    }
}
