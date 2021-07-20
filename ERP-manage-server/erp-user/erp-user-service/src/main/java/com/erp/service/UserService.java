package com.erp.service;

import com.erp.common.pojo.PageResult;
import com.erp.mapper.RoleMapper;
import com.erp.mapper.UserMapper;
import com.erp.pojo.Role;
import com.erp.pojo.User;
import com.erp.utils.CodeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    public Boolean checkData(String data, Integer type) {
        User record = new User();
        switch (type) {
            case 1:
                record.setUsername(data);
                break;
            case 2:
                record.setPhone(data);
                break;
            default:
                return null;
        }
        return this.userMapper.selectCount(record) == 0;
    }

    /**
     * 注册功能
     * @param user
     * @return
     */
    public void  register(User user) {
        // 生成盐
        String salt = CodeUtils.generateSalt();
        user.setSalt(salt);
        // 对密码进行加密
        user.setSalt(CodeUtils.md5Hex(user.getPassword(), salt));
        // 写入数据库
        user.setId(null);
        user.setCreated(new Date());
        this.userMapper.insertSelective(user);
    }

    public User queryUser(String username, String password) {

        User record = new User();
        record.setUsername(username);
        User user = this.userMapper.selectOne(record);

        //判断user是否为空
        if (user == null){
            return null;
        }
//        //获取盐，对用户输入的密码加盐加密
//        password = CodecUtils.md5Hex(password,user.getSalt());
//        //和数据库中的密码比较
//        if (StringUtils.equals(password,user.getPassword())){
//            return user;
//        }
//        return null;
        return user;
    }

    /**
     * 查询用户列表
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    public PageResult<User> queryUsersList(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        // 开始分页
        PageHelper.startPage(page, rows);
        // 过滤
        Example example = new Example(User.class);
        if (StringUtils.isNotBlank(key)) {
            example.createCriteria().andLike("username", "%" + key + "%")
                    .orEqualTo("id", key);
        }
        if (StringUtils.isNotBlank(sortBy)) {
            // 排序
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        // 查询
        Page<User> pageInfo = (Page<User>) userMapper.selectByExample(example);
        for(User u :pageInfo){
            u.setName(queryRole(u.getRid()));
        }
        // 返回结果
        return new PageResult<>(pageInfo.getTotal(), pageInfo);
    }

    public String queryRole(Long rid){
        Role record=new Role();
        record.setId(rid);
        Role role = this.roleMapper.selectOne(record);
        String rname=role.getName();
        return rname;
    }
    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    public List<User> queryByBrandId(Long id) {
        User record = new User();
        record.setId(id);
        User user = this.userMapper.selectOne(record);
        if (user == null){
            return null;
        }
        List<User> users = new ArrayList<User>();
        users.add(user);
        return users;
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteUser(Long id) {
        this.userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新用户
     * @param user
     */
    public void updateUser(User user) {
        this.userMapper.updateByPrimaryKeySelective(user);
    }
}
