package br.ifsp.contacts.dto.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDTO {
        @NotBlank(message = "A rua não pode estar vazia")
        String rua;

        @NotBlank(message = "A cidade não pode estar vazia")
        String cidade;

        @NotBlank(message = "O estado não pode estar vazio")
        @Size(min = 2, max = 2, message = "O estado deve ter exatamente 2 letras")
        @Pattern(regexp = "[A-Z]{2}", message = "O estado deve ser representado por duas letras maiúsculas")
        String estado;

        @NotBlank(message = "O CEP não pode estar vazio")
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 99999-999")
        String cep;
}
            