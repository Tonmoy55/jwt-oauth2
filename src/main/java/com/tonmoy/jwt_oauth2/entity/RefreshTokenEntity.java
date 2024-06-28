package com.tonmoy.jwt_oauth2.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "refresh_token")
public class RefreshTokenEntity {
    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

    @Column(name = "RefreshToken", nullable = false, length = 10000)
    private String refreshToken;

    @Column(name = "Revoked")
    private boolean revoked;

    @ManyToOne
    @JoinColumn(name = "UserId",referencedColumnName = "Id")
    private UserInfoEntity user;
}
