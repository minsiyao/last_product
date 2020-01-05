package com.minsiyao.service;

import com.minsiyao.entity.Admin;

public interface AdminService {
    public Admin queryById(String id);

    public Admin queryByUsername(String username);
}
