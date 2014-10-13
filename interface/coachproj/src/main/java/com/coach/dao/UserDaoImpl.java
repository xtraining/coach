package com.coach.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coach.model.User;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao{
	private static final Logger log = LoggerFactory
			.getLogger(UserDaoImpl.class);
	public int insert(User user) {
		return this.getSqlSession().insert("insert", user);
	}

	public User getUserById(long id) {
		return  this.getSqlSession().selectOne("getUserById", id);
	}

	@Override
	public void excludeMethod() {}

}
