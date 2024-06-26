package group4462.payment.service;

import group4462.payment.model.TransferObject;
import group4462.payment.exception.ResourceNotFoundException;
import group4462.payment.model.Account;
import group4462.payment.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    private AccountRepository repository;
    @InjectMocks
    private AccountService service;

    @Test
    public void paymentBookTest() throws ResourceNotFoundException {
        //Предпосылки
        TransferObject dataObject = new TransferObject();
        dataObject.setSenderName("Sender");
        dataObject.setAmount(BigDecimal.valueOf(500));
        dataObject.setReceiverName("bookstore");
        dataObject.setSumma(BigDecimal.valueOf(200));
        Account sender = new Account();
        sender.setId(1L);
        sender.setName("Sender");
        sender.setAmount(BigDecimal.valueOf(5));
        Account receiver = new Account();
        receiver.setId(2L);
        receiver.setName("bookstore");
        receiver.setAmount(BigDecimal.valueOf(0));
        when(repository.findAccountsByName("Sender")).thenReturn(Optional.of(sender));
        when(repository.findAccountsByName("bookstore")).thenReturn(Optional.of(receiver));
        //Вызов
        service.paymentBook(dataObject);
        //Проверка
        assertEquals(receiver.getAmount(), BigDecimal.valueOf(200));
        assertEquals(sender.getAmount(), BigDecimal.valueOf(305));
    }

    @Test
    public void findByNameTest() {
        //Предпосылки
        Account sender = new Account();
        sender.setId(1L);
        sender.setName("Sender");
        sender.setAmount(BigDecimal.valueOf(5));
        when(repository.findAccountsByName("Sender")).thenReturn(Optional.of(sender));
        //Вызов
        Optional<Account> account = service.findByName("Sender");
        //Проверка
        verify(repository).findAccountsByName("Sender");
        assertNotNull(account);
        assertEquals(Optional.of(sender), account);
    }
}