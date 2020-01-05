package com.minsiyao.service.impl;

import com.minsiyao.dao.GuruDao;
import com.minsiyao.entity.Guru;
import com.minsiyao.service.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GuruServiceImpl implements GuruService {
    @Autowired
    private GuruDao gd;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer queryToDay() {
        return gd.selectToDay();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer queryOnTwoDay() {
        return gd.selectOnTwoDay();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer queryOnThreeDay() {
        return gd.selectOnThreeDay();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer queryOnFourDay() {
        return gd.selectOnFourDay();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer queryOnFiveDay() {
        return gd.selectOnFiveDay();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer queryOnSixDay() {
        return gd.selectOnSixDay();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer queryOnSeven() {
        return gd.selectOnSeven();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer queryOnMouth(Integer mouth) {
        return gd.selectOnMouth(mouth);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer queryInSevenDays() {
        return gd.selectInSevenDays();
    }

    @Override
    public void insert(Guru guru) {
        gd.insert(guru);
    }

    @Override
    public void deleteById(String id) {
        gd.deleteById(id);
    }
}
