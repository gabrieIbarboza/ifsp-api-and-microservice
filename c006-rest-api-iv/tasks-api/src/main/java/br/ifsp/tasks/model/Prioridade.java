package br.ifsp.tasks.model;

import java.util.Arrays;

public enum Prioridade {
    BAIXA, MEDIA, ALTA;

    public static Prioridade fromString(String value) {
        return Arrays.stream(values()).filter(c -> c.name().equalsIgnoreCase(value)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid priority: " + value));
    }
}