package bancoexemploapi.app.service;

import bancoexemploapi.app.controller.ClienteRequest;
import bancoexemploapi.app.controller.ClienteResponse;
import bancoexemploapi.app.entities.Cliente;
import bancoexemploapi.app.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {
  private final ClienteRepository clienteRepository;

  public ClienteResponse createCliente(ClienteRequest request) {
    final var clienteResponse = new ClienteResponse();
    clienteResponse.setCode(201);
    clienteResponse.setMessage("Client created success");
    final var cliente = new Cliente();
    cliente.setName(request.getName());
    clienteRepository.save(cliente);
    return clienteResponse;
  }

  public ClienteResponse getCliente(String name) {
    final var cliente = clienteRepository.findByName(name);
    final var clienteResponse = new ClienteResponse();
    clienteResponse.setCode(200);
    clienteResponse.setMessage(cliente.getId() + "-" + cliente.getName());
    return clienteResponse;
  }

  public ClienteResponse updateCliente(Long id, String newName) {
    Optional<Cliente> clienteOptional = clienteRepository.findById(id);
    if (clienteOptional.isPresent()) {
      Cliente cliente = clienteOptional.get();
      cliente.setName(newName);
      clienteRepository.save(clienteOptional.get());
    } else {
      final var clienteResponseError = new ClienteResponse();
      clienteResponseError.setCode(400);
      clienteResponseError.setMessage("Client is not found");
      return clienteResponseError;
    }
    final var clienteResponse = new ClienteResponse();
    clienteResponse.setCode(200);
    clienteResponse.setMessage("Client is updated to " + clienteOptional.get().getName());
    return clienteResponse;
  }

  public ClienteResponse deleteCliente(Long id) {
    Optional<Cliente> clienteOptional = clienteRepository.findById(id);
    if (clienteOptional.isPresent()) {
      clienteRepository.delete(clienteOptional.get());
    } else {
      final var clienteResponseError = new ClienteResponse();
      clienteResponseError.setCode(400);
      clienteResponseError.setMessage("Client is not found");
      return clienteResponseError;
    }
    final var clienteResponse = new ClienteResponse();
    clienteResponse.setCode(200);
    clienteResponse.setMessage("Client is deleted");
    return clienteResponse;
  }
}
