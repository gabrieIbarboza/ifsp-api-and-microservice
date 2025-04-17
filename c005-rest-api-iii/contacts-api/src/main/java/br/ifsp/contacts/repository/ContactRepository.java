package br.ifsp.contacts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ifsp.contacts.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Esta interface extende JpaRepository, que nos fornece métodos prontos 
 * para acessar e manipular dados no banco de dados. Basta especificar 
 * a classe de entidade (Contact) e o tipo da chave primária (Long).
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    // Podemos adicionar métodos personalizados se necessário.
    Page<Contact> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
