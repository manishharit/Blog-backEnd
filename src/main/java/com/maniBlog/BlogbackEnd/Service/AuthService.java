package com.maniBlog.BlogbackEnd.Service;

import com.maniBlog.BlogbackEnd.Entity.User;
import com.maniBlog.BlogbackEnd.PayLoad.LoginDto;
import com.maniBlog.BlogbackEnd.PayLoad.RegisterDto;

import java.util.List;

public interface AuthService {

    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);

    List<User> getAllUsers();
}
