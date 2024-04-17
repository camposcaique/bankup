package bancoexemploapi.app.repository;

import bancoexemploapi.app.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

  Cliente findByName(String name);
}
