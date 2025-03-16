package com.Symple.Door.DTO.Recebo;

import jakarta.validation.constraints.NotBlank;

public record LoginEspDTO(@NotBlank String senha) {
}
