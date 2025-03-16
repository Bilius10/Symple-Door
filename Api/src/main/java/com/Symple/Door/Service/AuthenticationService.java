package com.Symple.Door.Service;

import com.Symple.Door.Repository.LoginR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private LoginR loginR;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            return loginR.findUserDetailsByName(username);
        } catch (UsernameNotFoundException u) {
            throw new RuntimeException(u);
        }


    }
}
