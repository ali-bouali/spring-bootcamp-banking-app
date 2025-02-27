package com.alibou.banking.transaction;

import com.alibou.banking.contact.ContactRequest;
import com.alibou.banking.user.UserRequest;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTransactionRequest {
    private TransactionRequest transactionRequest;
    private ContactRequest contactRequest;
}
