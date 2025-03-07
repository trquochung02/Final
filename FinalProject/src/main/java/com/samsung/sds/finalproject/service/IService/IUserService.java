package com.samsung.sds.finalproject.service.IService;

import com.samsung.sds.finalproject.entity.User;

public interface IUserService {
    User registerUser(User user);
    User findByUsername(String username);
}
