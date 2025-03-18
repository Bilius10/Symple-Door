package com.Symple.Door.DTO.Recebo;

import jakarta.validation.constraints.NotBlank;

public record RegistroDTO(@NotBlank String login,
                          @NotBlank String senha) {
}
