package com.danilo.minipicpay.services.transaction;

import com.danilo.minipicpay.dtos.TransactionDTO;
import com.danilo.minipicpay.entities.enums.TransactionStatus;
import com.danilo.minipicpay.entities.transaction.Transaction;
import com.danilo.minipicpay.entities.user.User;
import com.danilo.minipicpay.exceptions.InvalidSenderException;
import com.danilo.minipicpay.repositories.TransactionRepository;
import com.danilo.minipicpay.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Serviço responsável pela gestão das transações financeiras entre usuários na aplicação.
 * Este serviço realiza transações, valida os envolvidos e atualiza os saldos dos usuários.
 */
@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    /**
     * Realiza uma transação entre dois usuários, debitando o valor da conta do remetente e creditando na conta do destinatário.
     *
     * @param transaction Dados necessários para realizar a transação (quantia, remetente, destinatário, descrição, etc).
     * @throws InvalidSenderException Se o remetente e o destinatário forem os mesmos.
     * @throws IllegalArgumentException Se o valor da transação não for válido ou não houver saldo suficiente.
     * @apiNote Este método cria uma nova transação, valida os dados dos envolvidos e persiste as informações no banco de dados.
     * @responseStatus 200 OK Caso a transação seja realizada com sucesso.
     * @responseStatus 400 Bad Request Caso o remetente e o destinatário sejam os mesmos ou outros erros de validação.
     */
    public void makeTransaction(TransactionDTO transaction) {

        User sender = userService.findById(transaction.senderId());
        User reciver = userService.findById(transaction.reciverId());

        userService.validationTransaction(sender, reciver, transaction.amount());

        Transaction newTransaction = new Transaction();

        validateSenderAndReceiver(transaction.senderId(), transaction.reciverId());

        newTransaction.setAmount(transaction.amount());
        newTransaction.setSender(sender);
        newTransaction.setReciver(reciver);
        newTransaction.setTimestamp(LocalDateTime.now());
        newTransaction.setDescription(transaction.description());
        newTransaction.setStatus(TransactionStatus.PENDING);

        sender.setBalance(sender.getBalance().subtract(transaction.amount()));
        reciver.setBalance(reciver.getBalance().add(transaction.amount()));

        transactionRepository.save(newTransaction);
        userService.saveUser(sender);
        userService.saveUser(reciver);
    }

    /**
     * Recupera todas as transações realizadas na aplicação.
     *
     * @return Lista de todas as transações.
     * @apiNote Este método retorna todas as transações cadastradas no sistema.
     * @responseStatus 200 OK
     */
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    /**
     * Recupera uma transação específica pelo seu ID.
     *
     * @param id ID da transação.
     * @return A transação correspondente ao ID fornecido.
     * @throws RuntimeException Se a transação com o ID não for encontrada.
     * @apiNote Este método retorna a transação associada ao ID informado ou lança uma exceção caso não exista.
     * @responseStatus 200 OK Caso a transação seja encontrada.
     * @responseStatus 404 Not Found Caso a transação não seja encontrada.
     */
    public Transaction findById(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    /**
     * Valida se o remetente e o destinatário são diferentes.
     *
     * @param senderId ID do remetente.
     * @param receiverId ID do destinatário.
     * @throws InvalidSenderException Se o remetente e o destinatário forem os mesmos.
     * @apiNote Este método impede que uma transação seja realizada entre o mesmo usuário (remetente e destinatário).
     */
    private void validateSenderAndReceiver(Long senderId, Long receiverId) {
        if (senderId.equals(receiverId)) {
            throw new InvalidSenderException("Sender and receiver cannot be the same");
        }
    }
}
