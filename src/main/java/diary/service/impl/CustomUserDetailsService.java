package diary.service.impl;

import diary.dto.CustomUserDetails;
import diary.dto.User;
import diary.dto.UserRole;
import diary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userService.getUser(name);
        List<UserRole> userRoles = userService.getUserRole(user);
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (user == null) throw new UsernameNotFoundException("존재하지 않는 사용자입니다");

        if (userRoles != null) {
            for (UserRole userRole : userRoles) {
                authorities.add(new SimpleGrantedAuthority(userRole.getRoleName()));
            }
        }

        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setUsername(name);
        userDetails.setPassword(user.getPassword());
        userDetails.setAccountNonExpired(true);
        userDetails.setAccountNonLocked(true);
        userDetails.setEnabled(true);
        userDetails.setCredentialsNonExpired(true);
        userDetails.setAuthorities(authorities);

        return userDetails;
    }

}
