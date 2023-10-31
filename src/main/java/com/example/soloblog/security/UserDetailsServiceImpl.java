//package com.example.soloblog.security;
//
//import com.example.soloblog.repository.UserRepository;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    public UserDetailsServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
//        User user = userRepository.findByUserId(userId)
//                .orElseThrow(() -> new UsernameNotFoundException("Not Found " + userId));
//
//        return new UserDetailsImpl(user);
//    }
//}
