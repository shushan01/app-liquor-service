package com.app.framework.auth.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.app.framework.auth.dao.UserDao;
import com.app.framework.auth.model.LoginUser;
import com.app.framework.core.utils.Md5SaltUtil;
import com.app.framework.core.utils.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService extends BaseService<LoginUser> {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        super(userDao);
        this.userDao = userDao;
    }

    public boolean login(String dbPassword, String inpassword) {
        if (Md5SaltUtil.validPassword(inpassword, dbPassword)) {
            return true;
        } else {
            return false;
        }
    }

    public void updatePassword(String username, String password) {
        userDao.updatePassword(username, password);
    }

    public PageResult<LoginUser> findAll(int pageNo, int pageSize) {
        Page page = PageHelper.startPage(pageNo, pageSize);
        List<LoginUser> userList = userDao.selectAll();
        return new PageResult<LoginUser>(pageNo, pageSize, page.getTotal(), userList);
    }
}
