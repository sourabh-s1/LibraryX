package com.sourabh.libraryX.repository;

import com.sourabh.libraryX.model.LibraryBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<LibraryBook,String> {
}
