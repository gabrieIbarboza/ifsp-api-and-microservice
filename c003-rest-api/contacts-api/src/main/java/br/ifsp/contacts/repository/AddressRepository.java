package br.ifsp.contacts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ifsp.contacts.model.Address;

/**
 * Esta interface extende JpaRepository, que nos fornece métodos prontos 
 * para acessar e manipular dados no banco de dados. Basta especificar 
 * a classe de entidade (Address) e o tipo da chave primária (Long).
 */
public interface AddressRepository extends JpaRepository<Address, Long> {
    // Podemos adicionar métodos personalizados se necessário.
    List<Address> findByCidadeIgnoreCaseContaining(String substring);
    List<Address> findByContactId(Long contactId);
}