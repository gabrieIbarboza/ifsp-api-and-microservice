package br.ifsp.contacts.controller;

import br.ifsp.contacts.exception.ResourceNotFoundException;
import br.ifsp.contacts.model.Address;
import br.ifsp.contacts.repository.AddressRepository;
import br.ifsp.contacts.repository.ContactRepository;
import jakarta.validation.Valid;
import br.ifsp.contacts.model.Contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Controlador REST para gerenciar endereços.
 */
@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ContactRepository contactRepository;

    /**
     * Recupera todos os endereços.
     */
    @GetMapping
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @GetMapping("/contacts/{contactId}")
    public List<Address> getAddressesByContact(@PathVariable Long contactId) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado: " + contactId));
        
        return contact.getAddresses();
    }

    /**
     * Adiciona um novo endereço associado a um contato.
     */
    @PostMapping("/contacts/{contactId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Address createAddress(@PathVariable Long contactId, @RequestBody @Valid Address address) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado: " + contactId));
        
        address.setContact(contact);
        return addressRepository.save(address);
    }

    /**
     * Recupera endereços por cidade (busca parcial, ignorando maiúsculas/minúsculas).
     */
    @GetMapping("/search")
    public List<Address> searchByCity(@RequestParam String city) {
        return addressRepository.findByCidadeIgnoreCaseContaining(city);
    }

    /**
     * Atualiza um endereço existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address updatedAddress) {
        Address existingAddress = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado: " + id));

        // Atualiza os campos do endereço
        existingAddress.setRua(updatedAddress.getRua());
        existingAddress.setCidade(updatedAddress.getCidade());
        existingAddress.setEstado(updatedAddress.getEstado());
        existingAddress.setCep(updatedAddress.getCep());

        // Atualiza o contato associado, se necessário
        if (updatedAddress.getContact() != null) {
            Optional<Contact> contactOptional = contactRepository.findById(updatedAddress.getContact().getId());
            if (contactOptional.isEmpty()) {
                return ResponseEntity.badRequest().body(null);
            }
            existingAddress.setContact(contactOptional.get());
        }

        Address savedAddress = addressRepository.save(existingAddress);
        return ResponseEntity.ok(savedAddress);
    }

    /**
     * Atualiza parcialmente um endereço existente.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Address> patchAddress(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Address existingAddress = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado: " + id));

        // Aplica as atualizações parciais
        updates.forEach((key, value) -> {
            switch (key) {
                case "rua":
                    existingAddress.setRua((String) value);
                    break;
                case "cidade":
                    existingAddress.setCidade((String) value);
                    break;
                case "estado":
                    existingAddress.setEstado((String) value);
                    break;
                case "cep":
                    existingAddress.setCep((String) value);
                    break;
                case "contact":
                    if (value instanceof Map<?, ?>) {
                        Map<?, ?> contactMap = (Map<?, ?>) value;
                        if (contactMap.containsKey("id")) {
                            Long contactId = Long.valueOf(contactMap.get("id").toString());
                            Contact contact = contactRepository.findById(contactId)
                                    .orElseThrow(() -> new RuntimeException("Contato não encontrado: " + contactId));
                            existingAddress.setContact(contact);
                        } else {
                            throw new IllegalArgumentException("O campo 'id' está ausente no objeto 'contact'.");
                        }
                    } else {
                        throw new IllegalArgumentException("O valor fornecido para 'contact' não é um mapa válido.");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Campo inválido: " + key);
            }
        });

        Address savedAddress = addressRepository.save(existingAddress);
        return ResponseEntity.ok(savedAddress);
    }

    /**
     * Exclui um endereço pelo ID.
     */
    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable Long id) {
        addressRepository.deleteById(id);
    }
}