package kr.co.itcen.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.CategoryVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;
	
	public Boolean insert(String userId) {
		return 1==sqlSession.insert("category.insert", userId);
	}
	
	
	public CategoryVo get(String name, String userId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("id", userId);
		return sqlSession.selectOne("category.get", map);
	}
	public List<CategoryVo> getCategory(String userId) {
		return sqlSession.selectList("category.getList", userId);
	}
	public List<CategoryVo> getList(String id) {
		return sqlSession.selectList("category.list",id);
	}

	public Long insertcartegory(CategoryVo categoryvo) {
		int count=sqlSession.insert("category.insertcategory",categoryvo);
		return categoryvo.getNo();
	}


	public CategoryVo getCategory(Long no) {
		return sqlSession.selectOne("category.select",no);
	}


	public Boolean deletecategory(Long no) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("category.delete",no);
	}

	
}
