package com.minsiyao.service.impl;

import com.minsiyao.dao.ChapterDao;
import com.minsiyao.entity.Chapter;
import com.minsiyao.entity.Paging;
import com.minsiyao.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDao cd;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Paging queryByAlbumIdPaging(Integer page, Integer rows, String[] album_id) {
        System.out.println("album_id======" + album_id);
        Paging<Chapter> paging = new Paging();
        paging.setRowNum(rows);//设置每页行数
        Integer count = cd.selectByAlbumIdCount(album_id);
        paging.setRecords(count);//设置总条数
        Integer total = count % rows == 0 ? count / rows : count / rows + 1;
        paging.setTotal(total);//设置总页数
        paging.setPage(page);//设置当前页数
        paging.setRows(cd.selectByAlbumIdAndPaging((page - 1) * rows, rows, album_id));//添加数据
        System.out.println(paging);
        return paging;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer queryByAlbumIdCount(String[] album_id) {

        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Chapter> queryById(String[] id) {
        return cd.selectById(id);
    }

    @Override
    public void deleteById(String[] id) {
        cd.deleteById(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Chapter> queryAll() {
        return cd.selectAll();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Chapter> queryByAlbumId(String[] album_id) {
        return cd.selectByAlbumId(album_id);
    }

    @Override
    public void updateById(Chapter chapter) {
        cd.updateById(chapter);
    }

    @Override
    public Map<String, String> addChapter(Chapter chapter) {
        Map<String, String> map = new HashMap<>();
        try {
            cd.insert(chapter);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        map.put("id", chapter.getId());
        return map;
    }
}
