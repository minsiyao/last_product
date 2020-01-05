package com.minsiyao.dao;

import com.minsiyao.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    public Integer selectCountByAddress(@Param("address") String address);

    public void deleteById(@Param("id") String id);

    public User selectByPhone(@Param("phone") String phone);

    public void update(User user);
}
