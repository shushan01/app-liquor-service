package com.app.framework.auth.service;

import com.app.framework.core.utils.BaseDao;
import com.app.framework.core.utils.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import java.util.List;

/**
 * Created by Administrator on 2018/4/20 0020.
 */
public class BaseService<T> {
    private BaseDao<T> baseDao;

    public BaseService(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

    public int deleteById(Object o) {
        return baseDao.deleteByPrimaryKey(o);
    }

    public int delete(T t) {
        return baseDao.delete(t);
    }

    public void save(T t) {
        baseDao.insert(t);
    }

    public void saveSelective(T t) {
        baseDao.insertSelective(t);
    }

    public void saveGenKey(T t) {
        baseDao.insertUseGeneratedKeys(t);
    }

    /**
     * 返回保存的记录条数
     *
     * @param list
     * @return
     */
    public int saveBatch(List<T> list) {
        return baseDao.insertList(list);
    }

    /**
     * 只更新有值的字段，无值的字段不更新
     *
     * @param t
     * @return
     */
    public int update(T t) {
        return baseDao.updateByPrimaryKeySelective(t);
    }

    /**
     * 更新所有字段，无值字段设置为null
     *
     * @param t
     * @return
     */
    public int updateAll(T t) {
        return baseDao.updateByPrimaryKey(t);
    }

    public boolean exists(Object o) {
        return baseDao.existsWithPrimaryKey(o);
    }

    public List<T> findAll() {
        return baseDao.selectAll();
    }

    public PageResult<T> findAll(int pageNo, int pageSize) {
        Page<T> page = PageHelper.startPage(pageNo, pageSize);
        List<T> list = baseDao.selectAll();
        return new PageResult<T>(pageNo, pageSize, page.getTotal(), list);
    }

    public List<T> findBy(T t) {
        return baseDao.select(t);
    }

    public PageResult<T> findBy(T t, int pageNo, int pageSize) {
        Page<T> page = PageHelper.startPage(pageNo, pageSize);
        List<T> list = baseDao.select(t);
        return new PageResult<T>(pageNo, pageSize, page.getTotal(), list);
    }

    public T findOneBy(T t) {
        return baseDao.selectOne(t);
    }

    public int findCountBy(T t) {
        return baseDao.selectCount(t);
    }

    public T findById(Object o) {
        return baseDao.selectByPrimaryKey(o);
    }
}
