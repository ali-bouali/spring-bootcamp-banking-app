package com.alibou.banking.account;

import com.alibou.banking.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountMapperTest {

    private final AccountMapper accountMapper = new AccountMapper();

    @Test
    void should_map_account_correctly() {

        final String iban = "TN-12345";
        final User user = User.builder()
                .id(1L)
                .email("alibou@gmail.com")
                .firstName("Alibou")
                .lastName("Alibou")
                .active(true)
                .password("123456")
                .build();

        final Account mappedAccount = accountMapper.mapToAccountEntity(iban, user);

        assertEquals("TN-12345", mappedAccount.getIban());
        assertEquals(1, mappedAccount.getUser().getId());
        assertEquals("alibou@gmail.com", mappedAccount.getUser().getEmail());
        assertEquals("Alibou", mappedAccount.getUser().getFirstName());
        assertEquals("Alibou", mappedAccount.getUser().getLastName());
        assertEquals("123456", mappedAccount.getUser().getPassword());
        assertTrue(mappedAccount.isLocked());
    }

    @Test
    void should_not_map_to_account_if_user_is_null() {

        final String iban = "TN-12345";
        final NullPointerException exp = assertThrows(NullPointerException.class,
                 () -> accountMapper.mapToAccountEntity(iban, null));

         assertEquals("User should not be null", exp.getMessage());

    }

    @Test
    void should_not_map_to_account_if_iban_is_empty() {

        final NullPointerException exp = assertThrows(NullPointerException.class,
                () -> accountMapper.mapToAccountEntity("", new User()));

        assertEquals("Iban should not be empty or null", exp.getMessage());
    }

    @Test
    void should_not_map_to_account_if_iban_is_null() {

        final NullPointerException exp = assertThrows(NullPointerException.class,
                () -> accountMapper.mapToAccountEntity(null, new User()));

        assertEquals("Iban should not be empty or null", exp.getMessage());
    }

    @Test
    void should_not_map_to_account_if_iban_is_blank() {

        final NullPointerException exp = assertThrows(NullPointerException.class,
                () -> accountMapper.mapToAccountEntity(" ", new User()));

        assertEquals("Iban should not be empty or null", exp.getMessage());
    }

}