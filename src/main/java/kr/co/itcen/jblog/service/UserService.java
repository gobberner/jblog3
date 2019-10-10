package kr.co.itcen.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.UserDao;
import kr.co.itcen.jblog.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public void join(UserVo vo) {
		userDao.insert(vo);
	}

	public UserVo getUser(UserVo userVo) {
		return userDao.get(userVo);
	}

	public Boolean existUser(String id) {
		return userDao.get(id) != null;
	}
}
