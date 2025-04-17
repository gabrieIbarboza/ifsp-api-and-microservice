package br.ifsp.contacts.controller;
            
import org.modelmapper.ModelMapper;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ifsp.contacts.dto.contact.ContactPatchDTO;
import br.ifsp.contacts.dto.contact.ContactRequestDTO;
import br.ifsp.contacts.dto.contact.ContactResponseDTO;
import br.ifsp.contacts.exception.ResourceNotFoundException;
import br.ifsp.contacts.model.Contact;
import br.ifsp.contacts.repository.ContactRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Contatos", description = "API para gerenciamento de contatos")
@Validated
@RestController
@RequestMapping("/api/contacts")
public class ContactController {

        private final ContactRepository contactRepository;
        private final ModelMapper modelMapper;

        public ContactController(ContactRepository contactRepository, ModelMapper modelMapper) {
                this.contactRepository = contactRepository;
                this.modelMapper = modelMapper;
        }

        /**
         * Retorna uma lista paginada de todos os contatos.
         * 
         * @param pageable informações de paginação
         * @return página de contatos
         */
        @Operation(summary = "Listar todos os contatos", description = "Retorna uma lista paginada de todos os contatos cadastrados no sistema")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Contatos encontrados com sucesso"),
                        @ApiResponse(responseCode = "403", description = "Acesso negado", content = @Content)
        })
        @GetMapping
        public ResponseEntity<Page<ContactResponseDTO>> getAllContacts(Pageable pageable) {
                Page<Contact> contacts = contactRepository.findAll(pageable);
                Page<ContactResponseDTO> responseDTO = contacts
                                .map(contact -> modelMapper.map(contact, ContactResponseDTO.class));
                return ResponseEntity.ok(responseDTO);
        }

        /**
         * Busca um contato pelo ID.
         * 
         * @param id identificador do contato
         * @return contato encontrado
         * @throws ResourceNotFoundException se o contato não for encontrado
         */
        @Operation(summary = "Buscar contato por ID", description = "Retorna um contato específico com base no ID fornecido")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Contato encontrado com sucesso"),
                        @ApiResponse(responseCode = "404", description = "Contato não encontrado"),
                        @ApiResponse(responseCode = "403", description = "Acesso negado", content = @Content)
        })
        @GetMapping("/{id}")
        public ResponseEntity<ContactResponseDTO> getContactById(@PathVariable Long id) {
                Contact contact = contactRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado: " + id));

                ContactResponseDTO responseDTO = modelMapper.map(contact, ContactResponseDTO.class);
                return ResponseEntity.ok(responseDTO);
        }

        /**
         * Cria um novo contato.
         * 
         * @param dto dados do contato a ser criado
         * @return contato criado
         */
        @Operation(summary = "Criar novo contato", description = "Cria um novo contato com os dados fornecidos")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Contato criado com sucesso"),
                        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                        @ApiResponse(responseCode = "403", description = "Acesso negado", content = @Content)
        })
        @PostMapping
        public ResponseEntity<ContactResponseDTO> createContact(@Valid @RequestBody ContactRequestDTO dto) {
                Contact contact = modelMapper.map(dto, Contact.class);
                contact.getAddresses().forEach(address -> address.setContact(contact));
                Contact saved = contactRepository.save(contact);
                ContactResponseDTO responseDTO = modelMapper.map(saved, ContactResponseDTO.class);
                return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        }

        /**
         * Atualiza um contato existente.
         * 
         * @param id  identificador do contato
         * @param dto novos dados do contato
         * @return contato atualizado
         * @throws ResourceNotFoundException se o contato não for encontrado
         */
        @Operation(summary = "Atualizar contato", description = "Atualiza todos os dados de um contato existente")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Contato atualizado com sucesso"),
                        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                        @ApiResponse(responseCode = "404", description = "Contato não encontrado"),
                        @ApiResponse(responseCode = "403", description = "Acesso negado", content = @Content)
        })
        @PutMapping("/{id}")
        public ResponseEntity<ContactResponseDTO> updateContact(@PathVariable Long id,
                        @Valid @RequestBody ContactRequestDTO dto) {
                Contact existingContact = contactRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado: " + id));

                modelMapper.map(dto, existingContact);
                existingContact.getAddresses().forEach(address -> address.setContact(existingContact));

                Contact updated = contactRepository.save(existingContact);
                ContactResponseDTO responseDTO = modelMapper.map(updated, ContactResponseDTO.class);
                return ResponseEntity.ok(responseDTO);
        }

        /**
         * Atualiza parcialmente um contato existente.
         * 
         * @param id  identificador do contato
         * @param dto dados a serem atualizados
         * @return contato atualizado
         * @throws ResourceNotFoundException se o contato não for encontrado
         */
        @Operation(summary = "Atualizar contato parcialmente", description = "Atualiza apenas os campos especificados de um contato existente")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Contato atualizado com sucesso"),
                        @ApiResponse(responseCode = "404", description = "Contato não encontrado"),
                        @ApiResponse(responseCode = "403", description = "Acesso negado", content = @Content)
        })
        @PatchMapping("/{id}")
        public ResponseEntity<ContactResponseDTO> updateContactPartial(@PathVariable Long id,
                        @RequestBody ContactPatchDTO dto) {
                Contact existingContact = contactRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado: " + id));
                dto.getNome().ifPresent(existingContact::setNome);
                dto.getEmail().ifPresent(existingContact::setEmail);
                dto.getTelefone().ifPresent(existingContact::setTelefone);
                Contact saved = contactRepository.save(existingContact);
                ContactResponseDTO responseDTO = modelMapper.map(saved, ContactResponseDTO.class);
                return ResponseEntity.ok(responseDTO);
        }

        /**
         * Exclui um contato.
         * 
         * @param id identificador do contato
         * @return resposta sem conteúdo
         * @throws ResourceNotFoundException se o contato não for encontrado
         */
        @Operation(summary = "Excluir contato", description = "Remove permanentemente um contato do sistema")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "Contato excluído com sucesso"),
                        @ApiResponse(responseCode = "404", description = "Contato não encontrado"),
                        @ApiResponse(responseCode = "403", description = "Acesso negado", content = @Content)
        })
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
                if (!contactRepository.existsById(id)) {
                        throw new ResourceNotFoundException("Contato não encontrado: " + id);
                }
                contactRepository.deleteById(id);
                return ResponseEntity.noContent().build();
        }

        /**
         * Busca contatos pelo nome.
         * 
         * @param name     nome ou parte do nome a ser pesquisado
         * @param pageable informações de paginação
         * @return lista paginada de contatos que correspondem ao critério de busca
         */
        @Operation(summary = "Buscar contatos por nome", description = "Retorna uma lista paginada de contatos cujo nome contém o termo pesquisado")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
                        @ApiResponse(responseCode = "403", description = "Acesso negado", content = @Content)
        })
        @GetMapping("/search")
        public ResponseEntity<Page<ContactResponseDTO>> searchContactsByName(@RequestParam String name,
                        Pageable pageable) {
                Page<Contact> contacts = contactRepository.findByNomeContainingIgnoreCase(name, pageable);
                Page<ContactResponseDTO> responseDTO = contacts
                                .map(contact -> modelMapper.map(contact, ContactResponseDTO.class));
                return ResponseEntity.ok(responseDTO);
        }
}