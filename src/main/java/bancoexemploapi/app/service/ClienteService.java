package bancoexemploapi.app.service;

import bancoexemploapi.app.controller.ClienteRequest;
import bancoexemploapi.app.controller.ClienteResponse;
import bancoexemploapi.app.entities.Cliente;
import bancoexemploapi.app.exceptions.CustomNotFoundException;
import bancoexemploapi.app.mapstruct.MapperCliente;
import bancoexemploapi.app.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {
  private final ClienteRepository clienteRepository;
  private final MapperCliente mapperCliente;

  public ClienteResponse createCliente(ClienteRequest request) {
    final var cliente = mapperCliente.toCliente(request);
    clienteRepository.save(cliente);
    return mapperCliente.toResponse(cliente);
  }

  public ClienteResponse getCliente(String name) {
    final var cliente = clienteRepository.findByName(name);
    return mapperCliente.toResponse(cliente);
  }

  public ClienteResponse updateCliente(Long id, String newName) {
    Optional<Cliente> clienteOptional = clienteRepository.findById(id);
    if (clienteOptional.isPresent()) {
      Cliente cliente = clienteOptional.get();
      cliente.setName(newName);
      clienteRepository.save(clienteOptional.get());
    } else {
      throw new CustomNotFoundException("Client is not found");
    }
    return mapperCliente.toResponse(clienteOptional.get());
  }

  public ClienteResponse deleteCliente(Long id) {
    Optional<Cliente> clienteOptional = clienteRepository.findById(id);
    if (clienteOptional.isPresent()) {
      clienteRepository.delete(clienteOptional.get());
    } else {
      throw new CustomNotFoundException("Client is not found");
    }
    return mapperCliente.toResponse(clienteOptional.get());
  }
}
