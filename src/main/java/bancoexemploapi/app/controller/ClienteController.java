package bancoexemploapi.app.controller;

import bancoexemploapi.app.repository.ClienteRepository;
import bancoexemploapi.app.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class ClienteController {
  private final ClienteRepository clienteRepository;
  private final ClienteService clienteService;

  // POST = CREATE
  @PostMapping("create")
  public ResponseEntity<ClienteResponse> postCliente(@RequestBody ClienteRequest request) {
    return new ResponseEntity<>(clienteService.createCliente(request), HttpStatus.CREATED);
  }

  // GET = READ
  @GetMapping("read")
  public ResponseEntity<ClienteResponse> getCliente(@RequestParam String name) {
    return new ResponseEntity<>(clienteService.getCliente(name), HttpStatus.OK);
  }

  // PUT = UPDATE
  @PutMapping("put")
  public ResponseEntity<ClienteResponse> putCliente(@RequestParam Long id, String newName) {
    final var clienteResponse = clienteService.updateCliente(id, newName);
    if (clienteResponse.getCode() == 200) {
      return new ResponseEntity<>(clienteResponse, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(clienteResponse, HttpStatus.BAD_REQUEST);
    }
  }


  // DELETE = DELETE
  @DeleteMapping("delete")
  public ResponseEntity<ClienteResponse> deleteCliente(@RequestParam Long id) {
    final var clienteResponse = clienteService.deleteCliente(id);
    if (clienteResponse.getCode() == 200) {
      return new ResponseEntity<>(clienteResponse, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(clienteResponse, HttpStatus.BAD_REQUEST);
    }
  }
}
