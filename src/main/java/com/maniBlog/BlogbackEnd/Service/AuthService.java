package com.maniBlog.BlogbackEnd.Service;

import com.maniBlog.BlogbackEnd.PayLoad.LoginDto;

public interface AuthService {

    String login(LoginDto loginDto);
}
