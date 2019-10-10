package kr.co.itcen.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import kr.co.itcen.jblog.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlSession;

	public Boolean insert(UserVo vo) {

		StopWatch sw = new StopWatch();
		sw.start();
		int count = sqlSession.insert("user.insert", vo);

		sw.stop();
		Long totalTime = sw.getTotalTimeMillis();

		return count == 1;
	}

	public UserVo get(UserVo userVo) {
		UserVo result = sqlSession.selectOne("getByEmailAndPassword", userVo);
		return result;
	}

	public UserVo get(String id) { 
		UserVo result = sqlSession.selectOne("user.getById", id);
		return result;
	}
}
