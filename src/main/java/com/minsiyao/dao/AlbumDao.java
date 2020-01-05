package com.minsiyao.dao;

import com.minsiyao.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDao {
    public List<Album> selectAll();

    public void insert(Album album);

    public void update(Album album);

    public void deleteById(String[] id);

    public List<Album> selectById(String[] id);

    public List<Album> selectByPaging(@Param("start") Integer start, @Param("rows") Integer rows);

    public Integer selectCount();
}
