package org.hejianlin.my_mpc_test.security.user_detail;

import lombok.RequiredArgsConstructor;
import org.hejianlin.my_mpc_test.security.dto.User;
import org.hejianlin.my_mpc_test.security.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByName(username);
        return new UserDetailsImpl(user);
    }
}
