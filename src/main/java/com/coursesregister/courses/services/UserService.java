
package com.coursesregister.courses.services;

import com.coursesregister.courses.enums.Roles;
import com.coursesregister.courses.models.User;
import com.coursesregister.courses.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService implements UserDetailsService{
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        user.getAuthorities()))
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
    
    public void registerUser(String username, String password, Roles rol) throws Exception{

            Objects.requireNonNull(username, "Username can't be null");
            Objects.requireNonNull(password, "Password can't be null");
        
            if (userRepository.existsByUsername(username)) {
                throw new IllegalArgumentException("This username is registered");
            }
            
            User userToRegister = new User();
            userToRegister.setUsername(username);
            userToRegister.setPassword(passwordEncoder.encode(password));
            userToRegister.setRoles(rol);
            
            userRepository.save(userToRegister);
    }
}
