package com.mariza.hotel.service;

import com.mariza.hotel.dto.account.CreateAccountRequest;
import com.mariza.hotel.dto.account.UpdateAccountRequest;
import com.mariza.hotel.entity.Account;
import com.mariza.hotel.entity.Guest;
import com.mariza.hotel.repository.AccountRepository;
import com.mariza.hotel.repository.GuestRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

    @Service
    public class AccountService {

        private AccountRepository accountRepository;
        private String justCreatedPassword;
        private String hashedPassword;
        private String storedHashedPassword;
        private GuestRepository guestRepository;
        private Guest guest;

    public AccountService(AccountRepository accountRepository,GuestRepository guestRepository) {
        this.accountRepository = accountRepository;
        this.guestRepository = guestRepository;
    }

    public Account findByEmail(String email) {
        return  accountRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("Username not found"));

    }

    // hjälp metod som Notes program

    public boolean controlExistingUser(String email) {
        try {
            if (accountRepository.findByEmail(email)
                    .orElse(null) != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean createAccount(CreateAccountRequest createAccountRequest) {
        Account account = new Account();

        // 1- kontrollera om account finns
        try {
            if (controlExistingUser(createAccountRequest.getEmail())) {
                return false;
            } else {
                // 2. Hämta plaintext-lösenordet från AccountrequestDTO
                justCreatedPassword = createAccountRequest.getPasswordHash();

            }
            // 3. Hasha ny lösenordet med BCrypt
            hashedPassword = BCrypt.hashpw(justCreatedPassword, BCrypt.gensalt());

            // 4. Spara hash i account Objekt
            account.setPasswordHash(hashedPassword);

            guest = guestRepository.findById(createAccountRequest.getId())
                    .orElseThrow();

            // 5. alla andra information som behövs för att skapa konto
            account.setId(createAccountRequest.getId());
            account.setGuest(guest);
            account.setEmail(createAccountRequest.getEmail());
            account.setCreatedAt(LocalDateTime.now());
            account.setUpdatedAt(LocalDateTime.now());
            guest.setAccount(account);
            guestRepository.save(guest);
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage() + "Det gick inte att skapa account- Account Service klass");
        }
        return false;

        }

        public void deleteAccount(Long id) {
            accountRepository.deleteById(id);
        }

        public Account updateAccount(Long id, UpdateAccountRequest updateAccountRequest) {
            Account account = accountRepository.findById(id)
                    .orElseThrow(()-> new RuntimeException("Account not found"));
            account.setEmail(updateAccountRequest.getEmail());
            account.setPasswordHash(updateAccountRequest.getPasswordHash());
            account.setCreatedAt(LocalDateTime.now());
            account.setUpdatedAt(LocalDateTime.now());

            return accountRepository.save(account);
        }





}
