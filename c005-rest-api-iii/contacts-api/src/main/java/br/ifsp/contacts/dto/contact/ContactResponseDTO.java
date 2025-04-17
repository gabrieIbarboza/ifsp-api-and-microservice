package br.ifsp.contacts.dto.contact;

import java.util.List;

import br.ifsp.contacts.dto.address.AddressResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactResponseDTO {
        private Long id;
        private String nome;
        private String email;
        private String telefone;
        private List<AddressResponseDTO> addresses;
}
            