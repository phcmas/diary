package diary.service.impl;

import diary.dto.CustomUserDetails;
import diary.dto.User;
import diary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userService.getUser(name);
        if (user == null) throw new UsernameNotFoundException("존재하지 않는 사용자입니다");

        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setUsername(name);
        userDetails.setPassword(user.getPassword());
        userDetails.setAccountNonExpired(true);
        userDetails.setAccountNonLocked(true);
        userDetails.setEnabled(true);
        userDetails.setCredentialsNonExpired(true);

        return userDetails;
    }

}
