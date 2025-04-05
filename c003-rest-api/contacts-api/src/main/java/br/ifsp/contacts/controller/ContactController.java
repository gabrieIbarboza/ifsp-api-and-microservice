package br.ifsp.contacts.controller;

import br.ifsp.contacts.exception.ResourceNotFoundException;
import br.ifsp.contacts.model.Contact;
import br.ifsp.contacts.repository.ContactRepository;
import br.ifsp.contacts.model.Address;
import br.ifsp.contacts.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;


/**
 * Classe responsável por mapear as rotas/endpoints relacionados
 * aos contatos. Cada método abaixo corresponde a uma operação
 * em nosso sistema.
 * 
 * @RestController: Indica que esta classe é um controlador 
 *                  responsável por responder requisições REST.
 * @RequestMapping("/api/contacts"): Indica o caminho base.
 */
@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    /**
     * @Autowired permite que o Spring "injete" automaticamente
     * uma instância de ContactRepository aqui, 
     * sem que precisemos criar manualmente.
     */
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private AddressRepository addressRepository;

    /**
     * Método para obter todos os contatos.
     * 
     * @GetMapping indica que este método vai responder a chamadas HTTP GET.
     * Exemplo de acesso: GET /api/contacts
     */
    @GetMapping
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    /**
     * Método para obter um contato específico pelo seu ID.
     * 
     * @PathVariable "amarra" a variável {id} da URL 
     * ao parâmetro do método.
     * Exemplo de acesso: GET /api/contacts/1
     */
    @GetMapping("/{id}")
    public Contact getContactById(@PathVariable Long id) {
        // findById retorna um Optional, então usamos orElseThrow
        return contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado: " + id));
    }
    @GetMapping("/search")
    public List<Contact> getContactsByName(@RequestParam String name) {
        // findByName retorna uma lista de contatos que correspondem ao nome
        List<Contact> contacts = contactRepository.findByNomeIgnoreCaseContaining(name);
        return contacts;
    }
    

    /**
     * Método para criar um novo contato.
     * 
     * @PostMapping indica que este método responde a chamadas HTTP POST.
     * @RequestBody indica que o objeto Contact será preenchido 
     * com os dados JSON enviados no corpo da requisição.
     */
    @PostMapping
    public ResponseEntity<Contact> createContact(@Valid @RequestBody Contact contact) {
        Contact savedContact = contactRepository.save(contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedContact);
    }

    /**
     * Método para atualizar um contato existente.
     * 
     * @PutMapping indica que este método responde a chamadas HTTP PUT.
     * Exemplo de acesso: PUT /api/contacts/1
     */
    @PutMapping("/{id}")
    public Contact updateContact(@PathVariable Long id, @Valid @RequestBody Contact updatedContact) {
        // Buscar o contato existente
        Contact existingContact = contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado: " + id));

        // Atualizar os campos
        existingContact.setNome(updatedContact.getNome());
        existingContact.setTelefone(updatedContact.getTelefone());
        existingContact.setEmail(updatedContact.getEmail());

        // Salvar alterações
        return contactRepository.save(existingContact);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Contact> patchContact(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        // Find the contact by ID
        Contact contact = contactRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Contact not found with ID: " + id));

        // Apply updates to the contact
        updates.forEach((key, value) -> {
            switch (key) {
                case "nome":
                    contact.setNome((String) value);
                    break;
                case "telefone":
                    contact.setTelefone((String) value);
                    break;
                case "email":
                    contact.setEmail((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        // Save the updated contact
        Contact updatedContact = contactRepository.save(contact);

        // Return the updated contact
        return ResponseEntity.ok(updatedContact);
    }

    /**
     * Método para listar todos os endereços de um contato específico.
     * 
     * @GetMapping("/{id}/addresses") indica que este método responde a chamadas HTTP GET.
     * Exemplo de acesso: GET /api/contacts/1/addresses
     */
    @GetMapping("/{id}/addresses")
    public ResponseEntity<List<Address>> getAddressesByContactId(@PathVariable Long id) {
        // Verifica se o contato existe
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado: " + id));

        // Recupera os endereços associados ao contato
        List<Address> addresses = addressRepository.findAll().stream()
                .filter(address -> address.getContact().getId().equals(id))
                .toList();

        return ResponseEntity.ok(addresses);
    }

    /**
     * Método para excluir um contato pelo ID.
     * 
     * @DeleteMapping indica que este método responde a chamadas HTTP DELETE.
     * Exemplo de acesso: DELETE /api/contacts/1
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // Retorna o código 204 No Content
    }
}
