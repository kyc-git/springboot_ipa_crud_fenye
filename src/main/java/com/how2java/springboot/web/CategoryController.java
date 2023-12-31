package com.how2java.springboot.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.how2java.springboot.dao.CategoryDAO;
import com.how2java.springboot.pojo.Category;
 
@Controller
public class CategoryController {
	@Autowired CategoryDAO categoryDAO;
	
    @RequestMapping("/listCategory")
    public String listCategory(Model m,@RequestParam(value = "start", defaultValue = "0") int start,@RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
		System.out.println(start+"\n"+size);
    	//浏览器请求地址为../listCategory?start=1会让@RequestParam接收strat=1
    	start = start<0?0:start;
    	
    	Sort sort = new Sort(Sort.Direction.ASC, "id");//设置正排序
    	Pageable pageable = new PageRequest(start, size, sort);//定义分页规则：PageRequest对象包含了分页的相关信息，如页码、每页元素数量等。
    	Page<Category> page =categoryDAO.findAll(pageable);
    	//获取符合规则的  那一页   的数据+总页数+元素总数量

    	System.out.println(page.getNumber());
    	//获取当前页的页码，页码是从 0 开始的
    	System.out.println(page.getNumberOfElements());
    	//当前页实际元素数量；如果当前页没有元素，返回0
    	System.out.println(page.getSize());
    	//当前页实际元素数量；如果当前页没有元素，返回的是（页大小）
    	System.out.println(page.getTotalElements());//整个查询结果集中的元素总数量
    	System.out.println(page.getTotalPages());//获取总页数
		for(Category c:page.getContent())
			System.out.println(c);
    	m.addAttribute("page", page);
    	
        return "listCategory";
    }



	@RequestMapping("/addCategory")
    public String addCategory(Category c) throws Exception {
    	categoryDAO.save(c);
    	return "redirect:listCategory";
    }
    @RequestMapping("/deleteCategory")
    public String deleteCategory(Category c) throws Exception {
    	categoryDAO.delete(c);
    	return "redirect:listCategory";
    }
    @RequestMapping("/updateCategory")
    public String updateCategory(Category c) throws Exception {
    	categoryDAO.save(c);
    	return "redirect:listCategory";
    }
    @RequestMapping("/editCategory")
    public String editCategory(int id,Model m) throws Exception {
    	Category c= categoryDAO.getOne(id);
    	m.addAttribute("c", c);
    	return "editCategory";
    }
}

