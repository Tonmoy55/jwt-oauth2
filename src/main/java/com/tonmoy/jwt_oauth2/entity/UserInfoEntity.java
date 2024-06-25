package com.tonmoy.jwt_oauth2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "User_Info")
public class UserInfoEntity {
    @Id
    @Column(name = "Id")
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
    private String roles; //ROLE_MANAGER,ROLE_ADMIN --> [ROLE_MANAGER,ROLE_ADMIN]

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RefreshTokenEntity> refreshTokens;
}
