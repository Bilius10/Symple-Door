package com.Symple.Door.DTO.Devolvo;

import jakarta.validation.constraints.NotNull;

public record ErroDTO(@NotNull String mensagem) {
}
