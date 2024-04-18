package bancoexemploapi.app.mapstruct;

import bancoexemploapi.app.controller.ClienteRequest;
import bancoexemploapi.app.controller.ClienteResponse;
import bancoexemploapi.app.entities.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperCliente {

  Cliente toCliente(ClienteRequest request);

  ClienteResponse toResponse(Cliente cliente);
}
