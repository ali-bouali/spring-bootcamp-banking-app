package com.alibou.banking;

import com.alibou.banking.entities.account.AccountRepository;
import com.alibou.banking.entities.account.AccountEntity;
import com.alibou.banking.entities.address.AddressRepository;
import com.alibou.banking.entities.address.AddressEntity;
import com.alibou.banking.entities.contact.ContactRepository;
import com.alibou.banking.entities.contact.ContactEntity;
import com.alibou.banking.entities.fraud.FraudRepository;
import com.alibou.banking.entities.fraud.FraudEntity;
import com.alibou.banking.entities.fraud.FraudStatus;
import com.alibou.banking.entities.fraud.FraudType;
import com.alibou.banking.entities.role.RoleRepository;
import com.alibou.banking.entities.role.RoleEntity;
import com.alibou.banking.entities.transaction.TransactionRepository;
import com.alibou.banking.entities.transaction.TransactionEntity;
import com.alibou.banking.entities.transaction.TransactionStatus;
import com.alibou.banking.entities.users.UserRepository;
import com.alibou.banking.entities.users.Users;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class BankingAppApplication {

    public static void main(String[] args) {

        SpringApplication.run(BankingAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            UserRepository userRepository,
            AccountRepository accountRepository,
            AddressRepository addressRepository,
            ContactRepository contactRepository,
            FraudRepository fraudRepository,
            TransactionRepository transactionRepository, RoleRepository roleRepository) {
        return args -> {

            //creating fake users
            for (int i = 0; i < 50; i++) {
                Faker faker = new Faker();
                var myUser = Users.builder()
                        .firstName(faker.name().firstName())
                        .lastName(faker.name().lastName())
                        .email(faker.internet().emailAddress())
                        .password(faker.internet().password())
                        .build();
                userRepository.save(myUser);


                //Creating fake accounts

                var myAccount = AccountEntity.builder()
                        .iban(faker.finance().iban())
                        .users(myUser)
                        .build();
                accountRepository.save(myAccount);

                var myAddress = AddressEntity.builder()
                        .street(faker.address().streetAddress())
                        .city(faker.address().city())
                        .postalCode(faker.address().zipCode())
                        .country(faker.address().country())
                        .users(myUser)
                        .build();
                addressRepository.save(myAddress);

                var myContact = ContactEntity.builder()
                        .firstName(faker.name().firstName())
                        .lastName(faker.name().lastName())
                        .iban(faker.finance().iban())
                        .users(myUser)
                        .build();
                contactRepository.save(myContact);

                var myTransaction = TransactionEntity.builder()
                        .description(faker.lorem().sentence())
                        .amount(BigDecimal.valueOf(faker.number().randomDouble(2, 100, 1000000)))
                        .date(java.time.LocalDateTime.now())
                        .status(faker.options().option(TransactionStatus.class))
                        .destinationIban(faker.finance().iban())
                        .sourceIban(faker.finance().iban())
                        .users(myUser)
                        .build();
                transactionRepository.save(myTransaction);

                var myFraud = FraudEntity.builder()
                        .type(faker.options().option(FraudType.class))
                        .status(faker.options().option(FraudStatus.class))
                        .date(faker.date().past(10000, TimeUnit.DAYS).toInstant()
                                .atZone(java.time.ZoneId.systemDefault())
                                .toLocalDateTime())
                        .TransactionEntity(myTransaction)
                        .build();
                fraudRepository.save(myFraud);

                var myRole = RoleEntity.builder()
                        .name("ROLE_ADMIN")
                        .build();
                roleRepository.save(myRole);

                //Updating user with other classes references (ex : account_id) because the user was saved before the creation of the other classes
                // (ex: if not account_id will be null)

                myUser.setAccountEntity(myAccount);
                myUser.setAddressEntity(myAddress);
                myUser.setContactEntities(List.of(myContact));
                myUser.setTransactionEntities(List.of(myTransaction));
                myTransaction.setFraudEntity(myFraud);
                myRole.setUsers(List.of(myUser));
                userRepository.save(myUser);
                transactionRepository.save(myTransaction);
                roleRepository.save(myRole);

                //Finding accounts with iban hat ends with a specific four digits
                accountRepository.findByIbanStartingWith("MR")
                        .forEach(System.out::println);

                int updatedCount = accountRepository.updateIban("MR6461877496284755500021581", "DE12345678901234567890");
                if (updatedCount > 0) {
                    System.out.println("IBAN updated successfully!");
                } else {
                    System.out.println("No account found with the provided IBAN.");
                }
            }
        };
    }
}
