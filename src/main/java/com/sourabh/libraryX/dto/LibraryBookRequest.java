package com.sourabh.libraryX.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LibraryBookRequest(@NotBlank String name, @NotBlank String author, @NotNull String genre,@NotNull Integer publishYear,@NotNull Integer quantity) {
}
