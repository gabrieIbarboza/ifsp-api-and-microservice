package br.ifsp.contacts.dto.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDTO {
        private Long id;
        private String rua;
        private String cidade;
        private String estado;
        private String cep;
}
            