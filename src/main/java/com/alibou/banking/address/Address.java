package com.alibou.banking.address;




import com.alibou.banking.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ADDRESS")
public class Address {
    @Id
    @GeneratedValue
    private Long id;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


}
