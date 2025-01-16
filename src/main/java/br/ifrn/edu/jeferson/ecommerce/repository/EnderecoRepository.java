package br.ifrn.edu.jeferson.ecommerce.repository;


import br.ifrn.edu.jeferson.ecommerce.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Optional<Endereco> findByCliente_Id(Long id);
    boolean existsByCliente_Id(Long clienteId);
}
