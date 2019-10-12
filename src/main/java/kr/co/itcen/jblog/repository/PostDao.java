package kr.co.itcen.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.PostVo;

@Repository
public class PostDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<PostVo> mainPost(String userId) {
		return sqlSession.selectList("post.mainPost", userId);
	}
	public List<PostVo> categoryPost(Long cateNo) {
		return sqlSession.selectList("post.categoryPost", cateNo);
	}
	
	public PostVo getPost(Long cateNo, Long postNo) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("cateNo", cateNo);
		map.put("postNo", postNo);
		return sqlSession.selectOne("post.getPost", map);
	}
	public Boolean insertPost(PostVo vo) {
		return 1==sqlSession.insert("post.wirtePost",vo);
	}

}
