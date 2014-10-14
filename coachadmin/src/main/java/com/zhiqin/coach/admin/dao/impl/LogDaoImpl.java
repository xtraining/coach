package com.zhiqin.coach.admin.dao.impl;
import org.springframework.stereotype.Repository;

import com.zhiqin.coach.admin.dao.LogDao;
import com.zhiqin.coach.admin.entity.Log;


@Repository("logDao")
public class LogDaoImpl extends BaseDaoImpl<Log> implements LogDao
{
}
