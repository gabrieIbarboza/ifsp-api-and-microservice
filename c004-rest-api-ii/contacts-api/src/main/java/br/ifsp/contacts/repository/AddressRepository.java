package br.ifsp.contacts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.ifsp.contacts.model.Address;

/**
 * Esta interface extende JpaRepository, que nos fornece métodos prontos 
 * para acessar e manipular dados no banco de dados. Basta especificar 
 * a classe de entidade (Address) e o tipo da chave primária (Long).
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    // Podemos adicionar métodos personalizados se necessário.
    List<Address> findByCidadeIgnoreCaseContaining(String substring);
    Page<Address> findByContactId(Long contactId, Pageable pageable);
}