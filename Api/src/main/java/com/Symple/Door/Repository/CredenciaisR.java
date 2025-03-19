package com.Symple.Door.Repository;

import com.Symple.Door.Entity.CredenciaisE;
import com.Symple.Door.Entity.LoginE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredenciaisR extends JpaRepository<CredenciaisE, Long> {

    @Query("SELECT c FROM CredenciaisE c WHERE c.senha = :senha")
    Optional<LoginE> findCredenciaisEBySenha(String senha);
}
