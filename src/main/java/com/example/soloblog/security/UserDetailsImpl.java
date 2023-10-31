//package com.example.soloblog.security;
//

import java.util.Collection;

//public class UserDetailsImpl implements UserDetails {
//
//    private final User user;
//
//    public UserDetailsImpl(User user) {
//        this.user = user;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getUserId();
//    }
//
//
//    //접근불가 페이지에 사용
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
////        UserRoleEnum role = user.getRole();
////        String authority = role.getAuthority();
////
////        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
////        Collection<GrantedAuthority> authorities = new ArrayList<>();
////        authorities.add(simpleGrantedAuthority);
//
////        return authorities;
//
//        return null;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
