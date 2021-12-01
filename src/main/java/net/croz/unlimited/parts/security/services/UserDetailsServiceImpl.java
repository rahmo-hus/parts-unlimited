package net.croz.unlimited.parts.security.services;

import lombok.RequiredArgsConstructor;
import net.croz.unlimited.parts.model.users.User;
import net.croz.unlimited.parts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() ->new UsernameNotFoundException(username+" not found"));
        return UserDetailsImpl.build(user);
    }
}
