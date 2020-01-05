package com.minsiyao.service;

import com.minsiyao.entity.Banner;
import com.minsiyao.entity.Paging;

import java.util.List;

public interface BannerService {
    public List<Banner> queryAll();

    public void insert(Banner banner);

    public void deleteById(String[] id);

    public List<Banner> selectById(String[] id);

    public void update(Banner banner);

    public Paging queryByPaging(Integer page, Integer rows);

}
