package kr.co.itcen.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.PostVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;
	
	public BlogVo getBlogInfo(String userId) {
		return sqlSession.selectOne("blog.getById", userId);
	}
	
	public Boolean modifyBlogInfo(BlogVo vo) {
		return 1==sqlSession.update("blog.modifyBlogInfo", vo);
	}
	
	public Boolean writePost(PostVo vo) {
		return 1==sqlSession.insert("blog.wirtePost", vo);
	}

	public BlogVo get(String id) {
		return sqlSession.selectOne("blog.get",id);
	}

	public Boolean insert(String id) {
		return 1==sqlSession.insert("blog.insert",id);
	}

	public Boolean update(BlogVo vo) {
		return 1==sqlSession.update("blog.update",vo);
	}
}
