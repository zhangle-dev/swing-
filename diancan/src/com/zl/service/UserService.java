package com.zl.service;

/**
* @ClassName: UserService
* @Description: 用户操作相关接口
* @author 张乐
* @date 2018年4月5日 下午1:47:03
*
 */
public interface UserService {

	/**
	 * 
	* @Title: login
	* @Description: 登录
	* @param username 用户名
	* @param password 密码
	* @return int  0为管理员，1为普通永固，2为用户名或密码错误
	* @throws
	 */
	int login(String username,String password);
	
	/*void list();
	
	void delete(Integer id);
	
	void add(User user);
	
	void update(User user);*/
	
	
}
