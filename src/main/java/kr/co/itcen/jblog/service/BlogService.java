package kr.co.itcen.jblog.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.BlogDao;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.PostVo;


@Service
public class BlogService {
	
	@Autowired
	BlogDao blogDao;
	
	public Map<? extends String, ? extends Object> getAll(String id, Long categoryNo, Long postNo) {
		return null;
	}

	private List<CategoryVo> getCategoryList(String id) {
		return null;
	}

	private List<PostVo> getPostList(Long categoryNo) {
		return null;
	}

	private PostVo viewPost(Long postNo) {
		
		return null;
	}

	private BlogVo getBlogInfo(String id) {
		
		return null;
	}

}
