package com.mitocode.security;

import com.mitocode.model.User;
import com.mitocode.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//Clase S2
@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final IUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findOneByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("user not found" + username);
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        String rol = user.getRole().getName();
        roles.add(new SimpleGrantedAuthority(rol));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), roles
        );
    }
}
