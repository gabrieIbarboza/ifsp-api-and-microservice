package br.ifsp.contacts.dto.contact;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactPatchDTO {
    private Optional<String> nome = Optional.empty();
    private Optional<String> email = Optional.empty();
    private Optional<String> telefone = Optional.empty();
}
            