package com.alibou.banking;

import com.alibou.banking.entites.account.Account;
import com.alibou.banking.entites.contact.Contact;
import com.alibou.banking.entites.address.Address;
import com.alibou.banking.entites.role.Role;
import com.alibou.banking.repositories.AccountRepository;
import com.alibou.banking.repositories.AddressRepository;
import com.alibou.banking.repositories.ContactRepository;
import com.alibou.banking.repositories.RoleRepository;
import com.alibou.banking.repositories.UserRepository;
import com.alibou.banking.entites.user.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingAppApplication.class, args);

	}


	@Bean
	public CommandLineRunner commandLineRunner(UserRepository userRepository , ContactRepository contactRepository,
											   AccountRepository accountRepository , RoleRepository roleRepository, AddressRepository addressRepository) {
		return (args) -> {

			var account = Account.builder().iban("FR14 2004 1010 0505 0001 3M02 606").build();
			accountRepository.save(account);

			var contact = Contact.builder().firstName("Tayari").lastName("Omar").iban("FR14 2004 1010 0505 0001 3M02 607").build();

			var role = Role.builder().name("utilisateur").build();
			roleRepository.save(role);

			var adress = Address.builder()
					.street("123 Rue")
					.city("Sfax")
					.state("Sfax")
					.postalCode("3000")
					.country("Tunisia")
					.build();
			addressRepository.save(adress);

			var user = User.builder().email("tayariasma01@gmail.com").firstName("Asma").lastName("Tayari")
					.email("tayariasma01@gmail.com").password("asma1234").active(true).address(adress).role(role).account(account).build();
			userRepository.save(user);
		};
	}
}