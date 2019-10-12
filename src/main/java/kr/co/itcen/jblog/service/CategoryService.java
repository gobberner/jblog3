package kr.co.itcen.jblog.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.CategoryDao;
import kr.co.itcen.jblog.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	public List<CategoryVo> getList(String id) {
		
		return categoryDao.getList(id);
	}

	public List<CategoryVo> getCategoryList(String id) {
		
		return categoryDao.getCategory(id);
	}

	public Long insert(CategoryVo categoryvo) {
		
		return categoryDao.insertcartegory(categoryvo);
	}

	public Boolean delete(Long no) {
		// TODO Auto-generated method stub
		return categoryDao.deletecategory(no);
	}

	public CategoryVo get(Long no) {
		return categoryDao.getCategory(no);
	}

}
