package com.Symple.Door.DTO.Recebo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CredencialFull(@NotNull Long idCredencial,
                             @NotBlank String nome,
                             @NotBlank String senha) {
}
