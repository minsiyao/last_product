package com.minsiyao.service.impl;

import com.minsiyao.dao.UserDao;
import com.minsiyao.entity.User;
import com.minsiyao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao ud;

    @Override
    public void update(User user) {
        ud.update(user);
    }

    @Override
    public User queryByPhone(String phone) {
        return ud.selectByPhone(phone);
    }

    @Override
    public void deleteById(String id) {
        ud.deleteById(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer queryCountByAddress(String address) {
        return ud.selectCountByAddress(address);
    }
}
