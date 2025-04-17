package br.ifsp.contacts.ContactController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import br.ifsp.contacts.controller.ContactController;
import br.ifsp.contacts.dto.contact.ContactResponseDTO;
import br.ifsp.contacts.exception.ResourceNotFoundException;
import br.ifsp.contacts.model.Address;
import br.ifsp.contacts.model.Contact;
import br.ifsp.contacts.repository.ContactRepository;

@ExtendWith(MockitoExtension.class)
public class ContactControllerUnitTest {

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ContactController contactController;

    @Test
    void testGetContactById_ReturnsContact() {
            Long id = 1L;      
            Contact contact = new Contact("João", "joao@email.com", "11999999999");
            Address address = new Address();
            address.setRua("Rua A");
            address.setCidade("Cidade A");
            address.setEstado("SP");
            address.setCep("00000-000");
            List<Address> addresses = List.of(address);            
            contact.setAddresses(addresses);
            ReflectionTestUtils.setField(contact, "id", id); // ID via reflexão
        
            ContactResponseDTO dto = new ContactResponseDTO();
            dto.setId(id);
            dto.setNome("João");
            dto.setEmail("joao@email.com");
            dto.setTelefone("11999999999");
        
            when(contactRepository.findById(id)).thenReturn(Optional.of(contact));
            when(modelMapper.map(contact, ContactResponseDTO.class)).thenReturn(dto);
        
            ResponseEntity<ContactResponseDTO> response = contactController.getContactById(id);
        
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(id, (response.getBody() != null ? response.getBody().getId(): null));
    }

    @Test
    void testGetContactById_NotFound() {
        Long id = 999L;
        when(contactRepository.findById(id)).thenReturn(Optional.empty());
        
        assertThrows(ResourceNotFoundException.class, () -> contactController.getContactById(id));
    }
}