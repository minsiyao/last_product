package com.minsiyao.service;

import com.minsiyao.entity.User;

public interface UserService {
    public Integer queryCountByAddress(String address);

    public void deleteById(String id);

    public User queryByPhone(String phone);

    public void update(User user);
}
