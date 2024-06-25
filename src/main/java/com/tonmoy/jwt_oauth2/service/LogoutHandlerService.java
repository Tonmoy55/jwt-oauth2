package com.tonmoy.jwt_oauth2.service;

import com.tonmoy.jwt_oauth2.config.RSAKeyRecord;
import com.tonmoy.jwt_oauth2.config.jwtConfig.JwtTokenUtils;
import com.tonmoy.jwt_oauth2.repository.RefreshTokenRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutHandlerService implements LogoutHandler {
    private final RefreshTokenRepo refreshTokenRepo;
    private final RSAKeyRecord rsaKeyRecord;
    private final JwtTokenUtils jwtTokenUtils;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (!authHeader.startsWith(OAuth2AccessToken.TokenType.BEARER.getValue())) {
            return;
        }

        final JwtDecoder jwtDecoder = NimbusJwtDecoder.withPublicKey(rsaKeyRecord.rsaPublicKey()).build();
        final String refreshToken = authHeader.substring(7);
        final Jwt decodedRefreshToken = jwtDecoder.decode(refreshToken);
        var userEMail = jwtTokenUtils.getUserName(decodedRefreshToken);

        revokeRefreshToken(refreshToken);
        //revokeAllRefreshTokenForUser(userEMail);
    }

    private void revokeRefreshToken(String refreshToken) {
        refreshTokenRepo.findByRefreshToken(refreshToken)
                .ifPresent(refreshTokenEntity -> {
                    refreshTokenEntity.setRevoked(true);
                    refreshTokenRepo.save(refreshTokenEntity);
                });
    }

    private void revokeAllRefreshTokenForUser(String userEMail) {
        refreshTokenRepo.findAllRefreshTokenByUserEmailId(userEMail)
                .ifPresent(refreshTokenEntities -> {
                    refreshTokenEntities.forEach(refreshTokenEntity -> refreshTokenEntity.setRevoked(true));
                    refreshTokenRepo.saveAll(refreshTokenEntities);
                });
    }
}
