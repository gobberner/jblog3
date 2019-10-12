package kr.co.itcen.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.itcen.jblog.security.AuthUser;
import kr.co.itcen.jblog.service.BlogService;
import kr.co.itcen.jblog.service.CategoryService;
import kr.co.itcen.jblog.service.PostService;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.PostVo;
import kr.co.itcen.jblog.vo.UserVo;

@Controller
@RequestMapping("/{id:(?!assets)(?!images).*}")
public class BlogController {

	@Autowired
	private BlogService blogService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private PostService postService;

	@RequestMapping({ "", "/{pathNo1}", "/{pathNo1}/{pathNo2}" })
	public String index(@PathVariable String id, @PathVariable Optional<Long> pathNo1,
			@PathVariable Optional<Long> pathNo2, Model model, @AuthUser UserVo vo) {
		List<CategoryVo> list = categoryService.getList(id);// 해당 아이디로 해당 블로그 카테고리 모두 읽기
		model.addAttribute("list", list);
		Long categoryno = list.get(0).getNo();
		Long postno = 0L;
		if (pathNo2.isPresent()) {
			categoryno = pathNo1.get();
			postno = pathNo2.get();
		} else if (pathNo1.isPresent()) {
			categoryno = pathNo1.get();
		}
		List<PostVo> postList = postService.getList(categoryno);// 카테고리 no를 통해 포스트 리스트 모두읽기
		model.addAttribute("postList", postList);
		PostVo postvo;
		if (postno == 0L && (!postList.isEmpty())) {// 리스트가 비워있지 않고 패스로 암것도 안들어 오면
			postno = postList.get(0).getNo();
			postvo = postService.getPost(postno, categoryno);// postno,와 카테고리 no를 통해 해당 포스트 가지고 오기
		} else {
			postvo = postService.getPost(postno, categoryno);// 아무것도 없을때
		}
		BlogVo blogvo = blogService.get(id);// id로 해당 블로그 가지고 오기
		model.addAttribute("vo", blogvo);
		model.addAttribute("post", postvo);
		model.addAttribute("id", id);
		if (id.equals(vo.getId()))//
			model.addAttribute("isMe", true);
		return "blog/blog-main";
	}

	@RequestMapping("/admin/basic")
	public String blogbasic(@PathVariable String id, Model model) {
		BlogVo blogvo = blogService.get(id);
		model.addAttribute("vo", blogvo);
		return "blog/blog-admin-basic";
	}

	@RequestMapping(value = "/admin/basic", method = RequestMethod.POST)
	public String blogbasic(@RequestParam(value = "logo-file", required = false) MultipartFile multipartFile,
			@PathVariable String id, BlogVo vo) {
		vo.setId(id);
		blogService.update(multipartFile, vo);
		return "redirect:/" + id;
	}

	@RequestMapping("/admin/category")
	public String blogCategory(@PathVariable String id, Model model) {
		List<CategoryVo> list = categoryService.getCategoryList(id);
		model.addAttribute("list", list);
		BlogVo blogvo = blogService.get(id);
		model.addAttribute("vo", blogvo);
		return "blog/blog-admin-category";
	}

	@RequestMapping("/admin/write")
	public String blogPost(@PathVariable String id, Model model) {
		BlogVo blogvo = blogService.get(id);
		model.addAttribute("vo", blogvo);
		List<CategoryVo> list= categoryService.getList(id);
		model.addAttribute("list",list);
		return "blog/blog-admin-write";
	}
	@RequestMapping(value = "/admin/write", method = RequestMethod.POST)
	public String blogPost(@PathVariable String id, PostVo vo,@RequestParam("categoryNo") Long no) {
		System.out.println("~~~~~~~~~~~~~~" + no);
		vo.setCategoryNo(no);
		postService.insert(vo);
		return "redirect:/" + id;
	}

}
