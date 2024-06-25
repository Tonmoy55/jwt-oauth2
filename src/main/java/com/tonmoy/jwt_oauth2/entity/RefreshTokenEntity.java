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
@Table(name = "Refresh_Token")
public class RefreshTokenEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "RefreshToken", nullable = false, length = 10000)
    private String refreshToken;

    @Column(name = "Revoked")
    private boolean revoked;

    @ManyToOne
    @JoinColumn(name = "UserId",referencedColumnName = "Id")
    private UserInfoEntity user;
}
