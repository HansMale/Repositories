package cn.smbms.service.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.smbms.dao.user.UserMapper;
import cn.smbms.pojo.User;
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
	@Override
	public User login(String userCode, String userPassword) {
        User user = userMapper.getLoginUser(userCode);
        //匹配密码
        if(null != user){
            if(!user.getUserPassword().equals(userPassword))
                user = null;
        }else {
            user = null;
        }
        return user;
	}

	@Override
	public List<User> getUserList(Map<String, Object> map) {
		return userMapper.getUserList(map);
	}

	@Override
	public Integer getUserCount(String queryUserName, Integer queryUserRole) {
		return userMapper.getUserCount(queryUserName,queryUserRole);
	}

	@Override
	public boolean updatePwd(Integer id, String pwd) {
		boolean flag = false;
		if(userMapper.updatePwd(id, pwd)>0){
			flag = true;
		}
		return flag;
	}

	@Override
	public User getUserById(Integer id) {
		return userMapper.getUserById(id);
	}

	@Override
	public boolean add(User user) {
		boolean flag = false;
		if(userMapper.add(user)>0){
			flag = true;
		}
		return flag;
	}

	@Override
	public User selectUserCodeExist(String userCode) {
		return userMapper.getLoginUser(userCode);
	}

	@Override
	public boolean deleteUserById(Integer delId) {
		boolean flag = false;
		if(userMapper.deleteUserById(delId)>0){
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean modify(User user) {
		boolean flag = false;
		if(userMapper.modify(user)>0){
			flag = true;
		}
		return flag;
	}

}
