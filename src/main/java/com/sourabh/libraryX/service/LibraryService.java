package com.sourabh.libraryX.service;

import com.sourabh.libraryX.dto.LibraryBookResponse;
import com.sourabh.libraryX.repository.LibraryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LibraryService {
    @Autowired
    private final LibraryRepository libraryRepository;

    public List<LibraryBookResponse> getAllBooks() {
        List<LibraryBookResponse> books = libraryRepository.findAll().stream().map(
                e -> new LibraryBookResponse(e.getName(),e.getAuthor(),e.getGenre(),e.getPublishYear(),e.getQuantity())
        ).toList();
        return books;
    }
}
