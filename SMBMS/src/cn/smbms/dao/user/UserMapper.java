package cn.smbms.dao.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.User;

public interface UserMapper {
	public User getLoginUser(@Param("userCode")String userCode);
	
	public List<User> getUserList(Map<String, Object> map);
	
	public int getUserCount(@Param("userName")String userName,@Param("userRole")Integer userRole);
	
	public int updatePwd(@Param("id")Integer id,@Param("userPassword")String pwd);
	
	public User getUserById(@Param("id")Integer id);
	
	public int add(User user);
	 
	public int deleteUserById(@Param("id")Integer delId);
	
	public int modify(User user);
}
