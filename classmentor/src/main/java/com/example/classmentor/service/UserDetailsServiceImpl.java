package com.example.classmentor.service;

import com.example.classmentor.domain.User;
import com.example.classmentor.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(loginId)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + loginId));

        return UserDetailsImpl.build(user);
    }
}
