package io.swagger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AllAccountsWithoutTransactionsTest {


    private User bank;
    private AllAccountsWithoutTransactions account;


    @BeforeEach
    public void initialize(){
        bank = new User("bank", "bank123", "Inholland Bank ", "inhollandBank@bankapi.com", UserRole.ROLE_Employee, 1000.00, 500.00, Status.Active);
        account = new AllAccountsWithoutTransactions("NL01INHO0000000001", 10000.00, AccountType.Current, bank.getId(), 0.00, Status.Active, CurrencyType.EUR);
    }

    @Test
    public void creatingAccountWithTransactionsShouldNotBeNull(){
        assertNotNull(account);
    }
    @Test
    public void creatingAccountWithTransactionsIbanShouldBeNL01INHO0000000001(){
        assertEquals("NL01INHO0000000001", account.getIban());
    }
    @Test
    public void accountBalanceShouldBe10000(){
        assertEquals(10000.00, account.getBalance());
    }
    @Test
    public void accountTypeShouldBeCurrent(){
        assertEquals(AccountType.Current, account.getType());
    }
    @Test
    public void accountOwnerShouldBeBankId(){
        assertEquals(bank.getId(),account.getOwner());
    }
    @Test
    public void accountAbsoluteLimitShouldbe0(){
        assertEquals(0.00,account.getAbsoluteLimit());
    }
    @Test
    public void accountActiveShouldBeActive(){
        assertEquals(Status.Active,account.getActive());
    }



}
