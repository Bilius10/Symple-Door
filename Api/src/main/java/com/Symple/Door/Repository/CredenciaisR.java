package com.Symple.Door.Repository;

import com.Symple.Door.Entity.CredenciaisE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface CredenciaisR extends JpaRepository<CredenciaisE, Long> {

    @Query("SELECT c.nome FROM CredenciaisE c WHERE c.senha = :senha")
    String findCredenciaisEBySenha(String senha);
}
