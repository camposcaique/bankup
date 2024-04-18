package bancoexemploapi.app.controller;

import bancoexemploapi.app.exceptions.CustomNotFoundException;
import bancoexemploapi.app.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class ClienteController {
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
  public ResponseEntity<?> putCliente(@RequestParam Long id, String newName) {
    try {
      final var clienteResponse = clienteService.updateCliente(id, newName);
      return new ResponseEntity<>(clienteResponse, HttpStatus.OK);
    } catch (CustomNotFoundException e) {
      return new ResponseEntity<>(new ErrorResponse(
          400, "The client ID is not found"),
          HttpStatus.BAD_REQUEST);
    }
  }

  // DELETE = DELETE
  @DeleteMapping("delete")
  public ResponseEntity<?> deleteCliente(@RequestParam Long id) {
    try {
    final var clienteResponse = clienteService.deleteCliente(id);
    return new ResponseEntity<>(clienteResponse, HttpStatus.OK);
    } catch (CustomNotFoundException e) {
      return new ResponseEntity<>(new ErrorResponse(
              400, "Can't delete"),
              HttpStatus.BAD_REQUEST);
    }
  }
}
