package com.samsung.sds.finalproject.controller;

import com.samsung.sds.finalproject.config.JwtFilter;
import com.samsung.sds.finalproject.config.JwtUtil;
import com.samsung.sds.finalproject.dto.AuthRequest;
import com.samsung.sds.finalproject.entity.Role;
import com.samsung.sds.finalproject.entity.User;
import com.samsung.sds.finalproject.service.IService.IUserService;
import com.samsung.sds.finalproject.service.impl.CustomUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final IUserService userService;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtFilter jwtFilter;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, IUserService userService, CustomUserDetailsService customUserDetailsService, JwtFilter jwtFilter) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtFilter = jwtFilter;
    }

    // ✅ Đăng ký tài khoản mới
    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        user.setRole(Role.USER); // Mặc định tất cả user mới là USER
        userService.registerUser(user);
        return "redirect:/auth/login"; // Chuyển hướng đến trang đăng nhập
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register"; // Trả về trang đăng ký
    }

    // ✅ Đăng nhập
    @PostMapping("/login")
    public String login(@ModelAttribute AuthRequest request, HttpSession session) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtUtil.generateToken(userDetails);

        // Lưu user vào session
        session.setAttribute("user", request.getUsername());
        session.setAttribute("token", token);

        return "redirect:/home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // ✅ Đăng xuất
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpSession session) {
        String token = jwtUtil.extractToken(request);
        if (token != null) {
            jwtFilter.invalidateToken(token);
        }

        session.invalidate();
        return "redirect:/home";
    }
}
