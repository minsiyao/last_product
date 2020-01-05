package com.minsiyao.service.impl;

import com.minsiyao.dao.BannerDao;
import com.minsiyao.entity.Banner;
import com.minsiyao.entity.Paging;
import com.minsiyao.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bd;

    @Override
    public void update(Banner banner) {
        bd.update(banner);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Paging queryByPaging(Integer page, Integer rows) {
        Paging paging = new Paging();
        paging.setPage(page);
        paging.setRowNum(rows);
        Integer start = (paging.getPage() - 1) * paging.getRowNum();
        paging.setRows(bd.selectByPaging(start, paging.getRowNum()));
        Integer count = bd.selectCount();//数据条数
        paging.setRecords(count);//设置总条数
        paging.setTotal(count % paging.getRowNum() == 0 ? count / paging.getRowNum() : count / paging.getRowNum() + 1);//设置总页数
        System.out.println(paging);
        return paging;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Banner> selectById(String[] id) {
        return bd.selectById(id);
    }

    @Override
    public void deleteById(String[] id) {
        bd.deleteById(id);
    }

    @Override
    public void insert(Banner banner) {
        bd.insert(banner);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Banner> queryAll() {
        return bd.selectAll();
    }
}
