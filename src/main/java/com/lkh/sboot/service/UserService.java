package com.lkh.sboot.service;

import com.lkh.sboot.entity.User;
import com.lkh.sboot.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserService {
    @Resource
    private UserMapper userMapper;
    public List<Map<String,Object>> getList(String dept_id,String user_name,String user_realname,
                                            String user_phone,String user_sex){
        return userMapper.getList(dept_id,user_name,user_realname,user_phone,user_sex);
    }

    public boolean addUser(User user){
        if(userMapper.insert(user)>0){
            return true;
        }
        return false;
    }

    public boolean updateUser(User user){
        if(userMapper.updateByPrimaryKey(user)>0){
            return true;
        }
        return false;
    }

    public boolean delUser(String user_id){
        if(userMapper.deleteByPrimaryKey(user_id)>0){
            return true;
        }
        return false;

    }


   public User findByUsername(String user_name){
        List<User> list=userMapper.findByUsername(user_name);
        if (list!=null && list.size()==1){
            return list.get(0);
        }
        return null;
   }

}
