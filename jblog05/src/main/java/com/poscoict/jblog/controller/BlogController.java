package com.poscoict.jblog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.jblog.security.Auth;
import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.service.CategoryService;
import com.poscoict.jblog.service.FileUploadService;
import com.poscoict.jblog.service.PostService;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;

@Controller
@RequestMapping("/{id:(?!assets)(?!images).*}")
public class BlogController {
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping({"", "/{pathNo1}", "/{pathNo1}/{pathNo2}"})
	public String main(@PathVariable("id") String id,
						@PathVariable("pathNo1") Optional<Long> pathNo1,
						@PathVariable("pathNo2") Optional<Long> pathNo2,
						Model model) {
		System.out.println(id);
		Long categoryNo = 0L;
		Long postNo = 0L;
		
		if(pathNo2.isPresent()) {
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		}
		
		if(pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		servletContext.setAttribute("blogVo", blogService.findById(id));
		
		// 우측 카테고리 목록
		map.put("categorylist", categoryService.getCategoryById(id));
		
		if(categoryNo == 0L && postNo == 0L) {
			CategoryVo defaultCategory = categoryService.getDefaultCategoryNoById(id);
			map.put("postlist", categoryService.getPostByCategoryNo(defaultCategory.getNo()));
			map.put("post", postService.getPostOneByNo(defaultCategory.getNo()));
			
		} else if (postNo == 0L) {
			map.put("postlist", categoryService.getPostByCategoryNo(categoryNo));
			map.put("post", postService.getPostOneByNo(categoryNo));
		} else {
			map.put("postlist", categoryService.getPostByCategoryNo(categoryNo));
			map.put("post", postService.getPostByNo(postNo));
		}
		
		model.addAttribute("map", map);
		
		return "blog/blog-main";
	}
	
	@Auth
	@RequestMapping("/admin")
	public String admin() {
		return "blog/blog-admin-basic";
	}
	
	@Auth
	@RequestMapping("/admin/category")
	public String adminCategory(@PathVariable("id") String id, Model model) {
		
		List<CategoryVo> categoryList = categoryService.getCategoryById(id); 
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Long> categoryCntList = categoryService.getCategoryCnt(id);
		
		for(Long l : categoryCntList) {
			System.out.println("------------" + l);
		}
		
		map.put("categorylist", categoryList);
		map.put("categorycnt", categoryCntList);
		model.addAttribute("map", map);
		
		return "blog/blog-admin-category";
	}
	
	@Auth
	@RequestMapping("/admin/category/delete/{categoryno}")
	public String deleteCategory(@PathVariable("categoryno") Long categoryNo) {
		
		categoryService.delete(categoryNo);
		
		return "redirect:/{id}/admin/category";
	}
	
	@Auth
	@RequestMapping("/admin/category/insert")
	public String insertCategory(CategoryVo categoryVo) {
		categoryService.insertNew(categoryVo);
		
		return "redirect:/{id}/admin/category";
	}
	
	@Auth
	@RequestMapping("/admin/update")
	public String adminUpdate(@RequestParam(value = "logo-file") MultipartFile multipartfile, BlogVo blogVo, @PathVariable("id") String id) {
		
		String url = fileUploadService.restore(multipartfile);
		if(url != null) {
			blogVo.setLogo(url);
		}
		
		blogService.update(blogVo);
		
		servletContext.setAttribute("blogVo", blogService.findById(blogVo.getUserId()));
		
		return "redirect:/{id}";
	}
	
	@Auth
	@RequestMapping(value = "/admin/write", method = RequestMethod.GET)
	public String adminwrite(@PathVariable("id") String id,  Model model) {
		List<CategoryVo> categoryList = categoryService.getCategoryById(id); 
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("categorylist", categoryList);
		model.addAttribute("map", map);
		
		
		return "blog/blog-admin-write";
	}
	
	@Auth
	@RequestMapping(value = "/admin/write", method = RequestMethod.POST)
	public String adminwrite(PostVo postVo) {
		postService.insert(postVo);
		
		return "redirect:/{id}/admin/write";
	}
	
}
