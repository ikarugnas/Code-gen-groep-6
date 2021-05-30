package io.swagger.service;

import io.swagger.model.LoginDTO;
import io.swagger.model.RegisterDTO;
import io.swagger.model.Deposit;
import io.swagger.repository.DepositRepository;
import io.swagger.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DepositService {

    @Autowired
    DepositRepository depositRepository;


    public DepositService() {
    }

    public Deposit createDeposit(Deposit deposit){
        Deposit newDeposit = new Deposit(deposit.getUserPerforming(),
                deposit.getAccountTo(),
                deposit.getAmount(),
                deposit.getTransactionType(),
                deposit.getDateAndTime());

        depositRepository.save(deposit);

        return depositRepository.findAll().get(0);
    }
}
