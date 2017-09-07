package cn.smbms.service.user;

import java.util.List;
import java.util.Map;

import cn.smbms.pojo.User;

public interface UserService {
	 /**
     * 用户登录
     * @param userCode
     * @param userPassword
     * @return
     */
    public User login(String userCode,String userPassword);

    /**
     * 根据条件查询用户列表
     * @param map
     * @return
     */
    public List<User> getUserList(Map<String, Object> map);

    /**
     * 根据条件查询用户表记录数
     * @param queryUserName
     * @param queryUserRole
     * @return
     */
    public Integer getUserCount(String queryUserName,Integer queryUserRole);

    /**
     * 根据userId修改密码
     * @param id
     * @param pwd
     * @return
     */
    public boolean updatePwd(Integer id,String pwd);

    /**
     * 通过用户id查找用户
     * @param id
     * @return
     */
    public User getUserById(Integer id);

    /**
     * 添加用户
     * @param user
     * @return
     */
    public boolean add(User user);

    /**
     * 查看用户账号是否存在
     * @param userCode
     * @return
     */
    public User selectUserCodeExist(String userCode);

    /**
     * 删除用户
     * @param delId
     * @return
     */
    public boolean deleteUserById(Integer delId);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public boolean modify(User user);
}
