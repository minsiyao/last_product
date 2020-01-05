package com.minsiyao.dao;

import com.minsiyao.entity.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminDao {
    public Admin selectById(@Param("id") String id);

    public Admin selectByUsername(@Param("username") String username);
}
