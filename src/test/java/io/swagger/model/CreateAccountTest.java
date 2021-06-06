package io.swagger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreateAccountTest {
    private User bank;
    private CreateAccount account;

    @BeforeEach
    public void initialize(){
        bank = new User("bank", "bank123", "Inholland Bank ", "inhollandBank@bankapi.com", UserRole.ROLE_Employee, 1000.00, 500.00, Status.Active);
        account = new CreateAccount(0.0, Status.Active, bank.getUsername(),"Current", CurrencyType.EUR);
    }

    @Test
    public void creatingAccountWithTransactionsShouldNotBeNull(){
        assertNotNull(account);
    }
    @Test
    public void accountAbsoluteLimitShouldbe0(){
        assertEquals(0.00,account.getAbsoluteLimit());
    }
    @Test
    public void accountActiveShouldBeActive(){
        assertEquals(Status.Active,account.getActive());
    }
    @Test
    public void accountOwnerShouldBeBank(){
        assertEquals(bank.getUsername(),account.getOwner());
    }
    @Test
    public void accountTypeShouldBeCurrent(){
        assertEquals(AccountType.Current.toString(), account.getType().toString());
    }
}
