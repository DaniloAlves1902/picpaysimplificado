package com.danilo.minipicpay.repositories;

import com.danilo.minipicpay.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório responsável pela interação com o banco de dados para as entidades de usuário.
 * Fornece métodos para buscar usuários com base em seu documento e realizar operações CRUD comuns.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Encontra um usuário com base no seu documento.
     *
     * @param document O documento do usuário, geralmente CPF ou CNPJ.
     * @return Um {@link Optional} contendo o usuário, se encontrado.
     */
    Optional<User> findByDocument(String document);
}
