package com.minsiyao.service;

import com.minsiyao.entity.Album;
import com.minsiyao.entity.Paging;

import java.util.List;

public interface AlbumService {
    public List<Album> queryAll();

    public Paging queryByPaging(Integer page, Integer rows);

    public List<Album> queryById(String[] id);

    public String insert(Album album);

    public void update(Album album);

    public void deleteById(String[] id);
}
