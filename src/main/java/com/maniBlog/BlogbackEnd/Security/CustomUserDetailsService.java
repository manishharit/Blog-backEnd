package com.maniBlog.BlogbackEnd.Security;

import com.maniBlog.BlogbackEnd.Entity.User;
import com.maniBlog.BlogbackEnd.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
      User user = userRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail)
                .orElseThrow(()->
                        new UsernameNotFoundException("User Not found with given UserName or Email : " +usernameOrEmail));

        Set<GrantedAuthority> authorities = user.getRoles().stream().map(
                (role)-> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());


      return new org.springframework.security.core.userdetails.User(
              user.getEmail(),user.getPassword(),authorities);

    }
}
