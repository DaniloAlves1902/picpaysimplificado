package com.danilo.minipicpay.controllers.user;

import com.danilo.minipicpay.dtos.UserDTO;
import com.danilo.minipicpay.entities.user.User;
import com.danilo.minipicpay.services.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador responsável pela gestão dos usuários na API.
 * Este controlador expõe endpoints para a criação, listagem e exclusão de usuários.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Endpoint para listar todos os usuários.
     * 
     * @return Lista de usuários.
     * @apiNote Retorna todos os usuários cadastrados no sistema.
     * @responseStatus 200 OK
     */
    @GetMapping
    public List<UserDTO> listAll() {
        return userService.findAll();
    }

    /**
     * Endpoint para buscar um usuário pelo seu documento.
     * 
     * @param document Documento do usuário a ser buscado.
     * @return Usuário correspondente ao documento informado.
     * @apiNote Busca um usuário pelo seu número de documento.
     * @responseStatus 200 OK
     * @responseStatus 404 Not Found Caso o usuário não seja encontrado.
     */
    @GetMapping("/document/{document}")
    public User listByDocument(@PathVariable String document) {
        return userService.findByDocument(document);
    }

    /**
     * Endpoint para buscar um usuário pelo seu ID.
     * 
     * @param id ID do usuário a ser buscado.
     * @return Usuário correspondente ao ID informado.
     * @apiNote Busca um usuário pelo seu ID.
     * @responseStatus 200 OK
     * @responseStatus 404 Not Found Caso o usuário não seja encontrado.
     */
    @GetMapping("{id}")
    public User listById(@PathVariable Long id) {
        return userService.findById(id);
    }

    /**
     * Endpoint para criar um novo usuário.
     *
     * @param dataUser Dados do novo usuário a ser criado.
     * @return Usuário criado com sucesso.
     * @apiNote Cria um novo usuário com base nos dados fornecidos.
     * @requestBody UserDTO Dados do novo usuário a ser criado.
     * @responseStatus 201 Created
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid User dataUser) {
        User savedUser = userService.createUser(dataUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    /**
     * Endpoint para excluir um usuário.
     * 
     * @param id ID do usuário a ser excluído.
     * @apiNote Exclui o usuário correspondente ao ID fornecido.
     * @responseStatus 204 No Content Caso a exclusão seja bem-sucedida.
     * @responseStatus 404 Not Found Caso o usuário não seja encontrado.
     */
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
