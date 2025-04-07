package br.ifsp.contacts.dto.contact;

import java.util.List;

import br.ifsp.contacts.dto.address.AddressRequestDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactRequestDTO {
        @NotBlank(message = "O nome não pode estar vazio")
        private String nome;

        @NotBlank(message = "O email não pode estar vazio")
        @Email(message = "Formato de email inválido")
        private String email;

        @NotBlank(message = "O telefone não pode estar vazio")
        @Size(min = 8, max = 15, message = "O telefone deve ter entre 8 e 15 caracteres")
        @Pattern(regexp = "\\d+", message = "O telefone deve conter apenas números")
        private String telefone;

        @NotEmpty(message = "O contato deve ter pelo menos um endereço")
        private List<AddressRequestDTO> addresses;
}