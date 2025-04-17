package br.ifsp.contacts.controller;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ifsp.contacts.dto.address.AddressRequestDTO;
import br.ifsp.contacts.dto.address.AddressResponseDTO;
import br.ifsp.contacts.exception.ResourceNotFoundException;
import br.ifsp.contacts.model.Address;
import br.ifsp.contacts.model.Contact;
import br.ifsp.contacts.repository.AddressRepository;
import br.ifsp.contacts.repository.ContactRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Endereços", description = "API para gerenciamento de endereços")
@Validated
@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final ContactRepository contactRepository;
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    public AddressController(ContactRepository contactRepository, AddressRepository addressRepository, ModelMapper modelMapper) {
        this.contactRepository = contactRepository;
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Buscar todos os endereços de um contato", description = "Retorna uma lista paginada de endereços associados a um contato específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Endereços encontrados com sucesso"),
        @ApiResponse(responseCode = "404", description = "Contato não encontrado", content = @Content),
        @ApiResponse(responseCode = "403", description = "Acesso negado", content = @Content)
    })
    @GetMapping("/contacts/{contactId}")
    public ResponseEntity<Page<AddressResponseDTO>> getAddressesByContact(@PathVariable Long contactId, Pageable pageable) {
        Page<AddressResponseDTO> addresses = addressRepository.findByContactId(contactId, pageable)
                .map(address -> modelMapper.map(address, AddressResponseDTO.class));
        return ResponseEntity.ok(addresses);
    }

    @Operation(summary = "Criar novo endereço para um contato", description = "Cria um novo endereço associado a um contato específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Endereço criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Contato não encontrado", content = @Content),
        @ApiResponse(responseCode = "403", description = "Acesso negado", content = @Content)
    })
    @PostMapping("/contacts/{contactId}")
    public ResponseEntity<AddressResponseDTO> createAddress(@PathVariable Long contactId, @RequestBody @Valid AddressRequestDTO dto) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado: " + contactId));

        Address address = modelMapper.map(dto, Address.class);
        address.setContact(contact);
        Address saved = addressRepository.save(address);
        AddressResponseDTO responseDTO = modelMapper.map(saved, AddressResponseDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
