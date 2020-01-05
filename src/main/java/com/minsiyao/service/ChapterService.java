package com.minsiyao.service;

import com.minsiyao.entity.Chapter;
import com.minsiyao.entity.Paging;

import java.util.List;
import java.util.Map;

public interface ChapterService {
    public List<Chapter> queryAll();

    public Map<String, String> addChapter(Chapter chapter);

    public List<Chapter> queryByAlbumId(String[] album_id);

    public void updateById(Chapter chapter);

    public List<Chapter> queryById(String[] id);

    public void deleteById(String[] id);

    public Paging queryByAlbumIdPaging(Integer page, Integer rows, String[] album_id);

    public Integer queryByAlbumIdCount(String[] album_id);
}
