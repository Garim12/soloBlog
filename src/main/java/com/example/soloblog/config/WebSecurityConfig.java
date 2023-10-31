//package com.example.soloblog.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@EnableWebSecurity // Spring Security 지원을 가능하게 함
//@EnableGlobalMethodSecurity(securedEnabled = true)
//public class WebSecurityConfig {
//
//    private final JwtUtil jwtUtil;
//    private final UserDetailsServiceImpl userDetailsService;
//    private final AuthenticationConfiguration authenticationConfiguration;
//
//    public WebSecurityConfig(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService, AuthenticationConfiguration authenticationConfiguration) {
//        this.jwtUtil = jwtUtil;
//        this.userDetailsService = userDetailsService;
//        this.authenticationConfiguration = authenticationConfiguration;
//    }
//
//    // AuthenticationManager 생성
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//        return configuration.getAuthenticationManager();
//    }
//
//    // JwtAuthenticationFilter 에 AuthenticationManager를 넣어준다
//    @Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
//        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(jwtUtil);
//        filter.setAuthenticationManager(authenticationManager(authenticationConfiguration));
//        return filter;
//    }
//
//    @Bean
//    public JwtAuthorizationFilter jwtAuthorizationFilter() {
//        return new JwtAuthorizationFilter(jwtUtil, userDetailsService);
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        // CSRF 설정
//        http.csrf((csrf) -> csrf.disable());
//
//        // 기본 설정인 Session 방식은 사용하지 않고 JWT 방식을 사용하기 위한 설정
//        http.sessionManagement((sessionManagement) ->
//                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        );
//
//        // AuthFilter의 기능과 같지만 코드량이 적다 & 인증/인가를 할 url을 정의
//        http.authorizeHttpRequests((authorizeHttpRequests) ->
//                authorizeHttpRequests
//                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // resources 접근 허용 설정
//                        .requestMatchers("/","/user/**").permitAll()
//                        .anyRequest().authenticated() // 그 외 모든 요청 인증처리
//        );
//
//
//        // 필터 관리
//        http.addFilterBefore(jwtAuthorizationFilter(), JwtAuthenticationFilter.class);
//        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//
//
//        return http.build();
//    }
//}
