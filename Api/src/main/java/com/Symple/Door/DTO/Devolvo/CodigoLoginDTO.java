package com.Symple.Door.DTO.Devolvo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CodigoLoginDTO(@NotNull String codigo,
                             @NotBlank String nome,
                             @NotBlank String senha) {
}
