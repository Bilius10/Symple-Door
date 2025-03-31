package com.Symple.Door.Repository;

import com.Symple.Door.Entity.LoginE;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface LoginR extends JpaRepository<LoginE, Long> {

    @Query("SELECT l FROM LoginE l WHERE l.login = :login")
    UserDetails findUserDetailsByLogin(String login);

    @Query("SELECT l from LoginE l WHERE l.senha = :senha")
    Optional<LoginE> findLoginBySenha(String senha);

    @Query("SELECT l from LoginE l WHERE l.login = :login")
    Optional<LoginE> findLoginEByLogin(String login);

    @Transactional
    @Modifying
    @Query("DELETE from LoginE where login = :login")
    void deleteLoginEByLogin(String login);
}
