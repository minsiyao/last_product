package com.minsiyao.service.impl;

import com.minsiyao.dao.AdminDao;
import com.minsiyao.entity.Admin;
import com.minsiyao.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao ad;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Admin queryByUsername(String username) {
        return ad.selectByUsername(username);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Admin queryById(String id) {
        return ad.selectById(id);
    }
}
