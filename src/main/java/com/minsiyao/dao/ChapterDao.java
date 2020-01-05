package com.minsiyao.dao;

import com.minsiyao.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterDao {
    public List<Chapter> selectAll();

    public void insert(Chapter chapter);

    public void deleteById(String[] id);

    public List<Chapter> selectById(String[] id);

    public List<Chapter> selectByAlbumId(String[] album_id);

    public void updateById(Chapter chapter);

    public List<Chapter> selectByAlbumIdAndPaging(@Param("start") Integer start, @Param("rows") Integer rows, @Param("album_id") String[] album_id);

    public Integer selectByAlbumIdCount(@Param("album_id") String[] album_id);

}
