package net.croz.unlimited.parts.security.services.user;

import net.croz.unlimited.parts.models.users.User;
import net.croz.unlimited.parts.repository.UserRepository;
import net.croz.unlimited.parts.security.services.user.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() ->new UsernameNotFoundException(username+" not found"));
        return UserDetailsImpl.build(user);
    }
}
