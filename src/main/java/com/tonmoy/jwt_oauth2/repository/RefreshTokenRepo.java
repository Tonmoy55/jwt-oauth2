package com.tonmoy.jwt_oauth2.repository;


import com.tonmoy.jwt_oauth2.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.WebFilter;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshTokenRepo extends JpaRepository<RefreshTokenEntity, Long> {
    Optional<RefreshTokenEntity> findByRefreshToken(String refreshToken);

    @Query(value = "SELECT rt.* FROM refresh_token rt "+
            "INNER JOIN user_info ui ON rt.UserId = ui.Id "+
            "WHERE ui.EmailId = :userEmail and rt.Revoked = false ", nativeQuery = true)
    Optional<List<RefreshTokenEntity>> findAllRefreshTokenByUserEmailId(String userEmail);
}
