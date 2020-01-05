package com.minsiyao.service.impl;

import com.minsiyao.dao.AlbumDao;
import com.minsiyao.entity.Album;
import com.minsiyao.entity.Paging;
import com.minsiyao.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao ad;

    @Override
    public void deleteById(String[] id) {
        ad.deleteById(id);
    }

    @Override
    public void update(Album album) {
        ad.update(album);
    }

    @Override
    public String insert(Album album) {
        String id = UUID.randomUUID().toString();
        album.setCreate_date(new Date());
        album.setCount(0);
        album.setId(id);
        ad.insert(album);
        return id;
    }

    @Override
    public Paging queryByPaging(Integer page, Integer rows) {
        Paging paging = new Paging();
        paging.setPage(page);
        paging.setRowNum(rows);
        List<Album> albums = ad.selectByPaging((page - 1) * rows, rows);
        paging.setRows(albums);
        Integer count = ad.selectCount();
        paging.setRecords(count);
        paging.setTotal(count % rows == 0 ? count / rows : count / rows + 1);
        System.out.println(paging);
        return paging;
    }

    @Override
    public List<Album> queryById(String[] id) {
        return ad.selectById(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Album> queryAll() {
        return ad.selectAll();
    }
}
