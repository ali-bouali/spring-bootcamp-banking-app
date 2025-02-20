package com.alibou.banking;

import com.alibou.banking.account.Account;
import com.alibou.banking.account.AccountRepository;
import com.alibou.banking.address.AddressRepository;
import com.alibou.banking.contact.ContactRepository;
import com.alibou.banking.role.Role;
import com.alibou.banking.role.RoleRepository;
import com.alibou.banking.role.service.RoleService;
import com.alibou.banking.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Date;

@SpringBootApplication
@Slf4j
public class BankingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingAppApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(RoleService roleService) {
		return (args) -> {
		/*var roleAdmin = Role.builder().name("Admin").createdAt(LocalDateTime.now()).build();
			roleService.saveRole(roleAdmin);*/

	/*		var roleClient = Role.builder().name("Client").createdAt(LocalDateTime.now()).build();
			roleService.saveRole(roleClient);

			var roleManager = Role.builder().name("Manager").createdAt(LocalDateTime.now()).build();
			roleService.saveRole(roleManager);*/

			/*var t= roleService.getRole(2L);
			ResponseEntity.ok(t);
			log.info("this role "+t.getName());
			log.info(String.valueOf(ResponseEntity.ok(t).getBody()));



		/*	var account = Account.builder().iban("FR14 2004 1010 0505 0001 3M02 606").build();
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
			userRepository.save(user);*/
		};
	}







}
