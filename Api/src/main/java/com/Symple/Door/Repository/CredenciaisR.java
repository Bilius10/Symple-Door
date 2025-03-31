package com.Symple.Door.Repository;

import com.Symple.Door.Entity.CredenciaisE;
import com.Symple.Door.Entity.LoginE;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredenciaisR extends JpaRepository<CredenciaisE, Long> {

    @Query("SELECT c FROM CredenciaisE c WHERE c.senha = :senha")
    Optional<CredenciaisE> findCredenciaisEBySenha(String senha);

    @Transactional
    @Modifying
    @Query("DELETE FROM CredenciaisE c WHERE c.nome = :nome")
    void deleteCredenciaisEByNome( String nome);

    @Query("SELECT c FROM CredenciaisE c WHERE c.nome = :nome")
    Optional<CredenciaisE> findCredenciaisEByNome(String nome);
}
