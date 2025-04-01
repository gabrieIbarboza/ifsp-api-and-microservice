package br.ifsp.contacts.controller;

import br.ifsp.contacts.model.Address;
import br.ifsp.contacts.repository.AddressRepository;
import br.ifsp.contacts.repository.ContactRepository;
import br.ifsp.contacts.model.Contact;

import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * Adiciona um novo endereço associado a um contato.
     */
    @PostMapping
    public ResponseEntity<Address> addAddress(@RequestBody Address address) {
        // Verifica se o contato associado existe
        Optional<Contact> contactOptional = contactRepository.findById(address.getContact().getId());
        if (contactOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        // Obtém o contato associado
        Contact contact = contactOptional.get();

        // Adiciona o endereço ao contato, evitando duplicações
        if (!contact.getAddresses().contains(address)) {
            contact.getAddresses().add(address);
        }

        // Define o contato no endereço e salva
        address.setContact(contact);
        Address savedAddress = addressRepository.save(address);

        // Salva o contato atualizado
        contactRepository.save(contact);

        return ResponseEntity.ok(savedAddress);
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
                    Map<String, Object> contactMap = (Map<String, Object>) value;
                    Long contactId = Long.valueOf(contactMap.get("id").toString());
                    Contact contact = contactRepository.findById(contactId)
                            .orElseThrow(() -> new RuntimeException("Contato não encontrado: " + contactId));
                    existingAddress.setContact(contact);
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