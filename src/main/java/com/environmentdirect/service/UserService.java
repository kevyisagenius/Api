package com.environmentdirect.service;

import com.environmentdirect.dto.UserRegistrationDto;
import com.environmentdirect.model.User;
import com.environmentdirect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service for user-related operations.
 * Implements UserDetailsService for Spring Security authentication.
 */
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Load user by username for Spring Security authentication.
     * 
     * @param username the username to search for
     * @return UserDetails object containing user information
     * @throws UsernameNotFoundException if the user is not found
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Convert roles to SimpleGrantedAuthority objects
        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        // Return Spring Security User object
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }

    /**
     * Register a new user.
     * 
     * @param registrationDto the user registration data
     * @return the created user
     * @throws IllegalArgumentException if the username or email is already taken
     */
    @Transactional
    public User registerUser(UserRegistrationDto registrationDto) {
        // Check if username is already taken
        if (userRepository.existsByUsername(registrationDto.username())) {
            throw new IllegalArgumentException("Username is already taken");
        }

        // Check if email is already taken
        if (userRepository.existsByEmail(registrationDto.email())) {
            throw new IllegalArgumentException("Email is already registered");
        }

        // Create new user with encoded password
        User user = new User(
                registrationDto.username(),
                passwordEncoder.encode(registrationDto.password()),
                registrationDto.email()
        );

        // Save and return the new user
        return userRepository.save(user);
    }

    /**
     * Find a user by username.
     * 
     * @param username the username to search for
     * @return an Optional containing the user if found, or empty if not found
     */
    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Find a user by ID.
     * 
     * @param id the user ID to search for
     * @return an Optional containing the user if found, or empty if not found
     */
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
