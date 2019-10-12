package kr.co.itcen.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.co.itcen.jblog.repository.PostDao;
import kr.co.itcen.jblog.vo.PostVo;

@Service
public class PostService {
	
	@Autowired
	private PostDao postDao;

	public Map<String, Object> getCatePost(Long cateNo, Long postNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentPost", postDao.getPost(cateNo, postNo));
		map.put("postList", postDao.categoryPost(cateNo));
		return map;
	}

	public List<PostVo> mainPost(String userId) {
		return postDao.mainPost(userId);
	}

	public List<PostVo> categoryPost(Long cateNo) {
		return postDao.categoryPost(cateNo);
	}

	public PostVo getPost(Long cateNo, Long postNo) {
		return postDao.getPost(cateNo, postNo);
	}

	public List<PostVo> getList(Long categoryno) {
		return postDao.categoryPost(categoryno);
	}

}
