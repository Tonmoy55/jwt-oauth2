package com.tonmoy.jwt_oauth2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "UserInfo")
public class UserInfoEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "UserName")
    private String userName;

    @Column(nullable = false, name = "EmailId", unique = true)
    private String emailId;

    @Column(nullable = false, name = "Password")
    private String password;

    @Column(name = "MobileNumber")
    private String mobileNumber;

    @Column(nullable = false, name = "Roles")
    private String roles; //Role_Admin,Role_Manager --> [Role_Admin,Role_Manager]
}
