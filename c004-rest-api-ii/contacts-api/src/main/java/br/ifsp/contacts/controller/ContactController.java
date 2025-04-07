package br.ifsp.contacts.controller;
            
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.ifsp.contacts.dto.contact.ContactPatchDTO;
import br.ifsp.contacts.dto.contact.ContactRequestDTO;
import br.ifsp.contacts.dto.contact.ContactResponseDTO;
import br.ifsp.contacts.exception.ResourceNotFoundException;
import br.ifsp.contacts.model.Address;
import br.ifsp.contacts.model.Contact;
import br.ifsp.contacts.repository.ContactRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/contacts")
@Tag(name = "Contatos", description = "Operações relacionadas a contatos")
@Validated
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Operation(summary = "Buscar todos os contatos paginados")
    @GetMapping
    public Page<ContactResponseDTO> getAllContacts(Pageable pageable) {
        Page<Contact> contacts = contactRepository.findAll(pageable);
        return contacts.map(contact -> modelMapper.map(contact, ContactResponseDTO.class));
    }

    @Operation(summary = "Buscar contato por ID")
    @GetMapping("{id}")
    public ContactResponseDTO getContactById(@PathVariable Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado: " + id));
        return modelMapper.map(contact, ContactResponseDTO.class);
    }

    @Operation(summary = "Criar novo contato")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactResponseDTO createContact(@Valid @RequestBody ContactRequestDTO dto) {
        // Mapeia os campos simples
        Contact contact = new Contact(dto.getNome(), dto.getEmail(), dto.getTelefone());

        // Mapeia os endereços manualmente
        var addresses = dto.getAddresses().stream()
                .map(addrDto -> {
                    Address address = new Address();
                    address.setRua(addrDto.getRua());
                    address.setCidade(addrDto.getCidade());
                    address.setEstado(addrDto.getEstado());
                    address.setCep(addrDto.getCep());
                    address.setContact(contact); 
                    return address;
                }).toList();

        contact.setAddresses(addresses);

        Contact saved = contactRepository.save(contact);
        return modelMapper.map(saved, ContactResponseDTO.class);
    }

    @Operation(summary = "Atualizar contato por ID")
    @PutMapping("/{id}")
    public ContactResponseDTO updateContact(@PathVariable Long id, @Valid @RequestBody ContactRequestDTO dto) {
        Contact existingContact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado: " + id));

        modelMapper.map(dto, existingContact);
        existingContact.getAddresses().forEach(addr -> addr.setContact(existingContact));
        Contact updated = contactRepository.save(existingContact);
        return modelMapper.map(updated, ContactResponseDTO.class);
    }

    @Operation(summary = "Atualização parcial de contato")
    @PatchMapping("/{id}")
    public ContactResponseDTO updateContactPartial(@PathVariable Long id, @RequestBody ContactPatchDTO dto) {
        Contact existingContact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado: " + id));

        dto.getNome().ifPresent(existingContact::setNome);
        dto.getEmail().ifPresent(existingContact::setEmail);
        dto.getTelefone().ifPresent(existingContact::setTelefone);

        Contact saved = contactRepository.save(existingContact);
        return modelMapper.map(saved, ContactResponseDTO.class);
    }

    @Operation(summary = "Excluir contato")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@PathVariable Long id) {
        contactRepository.deleteById(id);
    }

    @Operation(summary = "Buscar contatos pelo nome")
    @GetMapping("/search")
    public Page<ContactResponseDTO> searchContactsByName(@RequestParam String name, Pageable pageable) {
        return contactRepository.findByNomeIgnoreCaseContaining(name, pageable)
                .map(contact -> modelMapper.map(contact, ContactResponseDTO.class));
    }
}
            