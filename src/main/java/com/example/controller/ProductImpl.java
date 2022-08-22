package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.controller.Exceptions.RecordNotFoundExceptions;
import com.example.controller.mapper.Mapper;
import com.example.dto.ProductDtoImpl;
import com.example.service.IProductManagement;
import com.example.vo.ProductVoImpl;

@Controller
@RequestMapping(value = "ProductManagement")
public class ProductImpl {

	@Autowired
	private IProductManagement service;

	@GetMapping(value = "welcome")
	public ModelAndView showHome(ModelAndView modelAndView, HttpServletRequest request) {
	
		return showPages(modelAndView, 1);
	}

	@GetMapping(value = "/PAGE/{page}")
	public ModelAndView showPages(ModelAndView modelAndView, @PathVariable("page") Integer value) {
		Pageable pageable = null;
         System.out.println(">>>>>>>>>>>>>>>>>>M<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"+value);
		pageable = PageRequest.of(value - 1, 10, Sort.by(Order.asc("name")));
		List<ProductVoImpl> retriveList =Mapper.toGetProductVoList(service.findAllRecords(pageable));
		
		modelAndView.addObject("currentPage", value);
		modelAndView.addObject("values", retriveList);
		modelAndView.addObject("totalRecord", service.getAllRecodsCount(pageable));
		modelAndView.addObject("totalPages", service.getTotalPages(pageable));
		modelAndView.setViewName("Home");

		return modelAndView;
	}

	@GetMapping(value = "add")
	public ModelAndView addMore(@ModelAttribute("form") ProductVoImpl product, HttpServletRequest request,
			ModelAndView formData, @RequestParam(required = false, defaultValue = "0") Integer id) throws Exception {

		ProductDtoImpl dtoImpl = Mapper.toGetProductDtoImpl(product);
		if (id != 0 && id != null) {
			ProductDtoImpl proDtoImpl = service.findByID(id);
			proDtoImpl.setId(id);
			ProductVoImpl voImpl=new ProductVoImpl();
			BeanUtils.copyProperties(proDtoImpl, voImpl);
			formData.setViewName("AddItem");
			formData.addObject("form", voImpl);
			return formData;
		}
		formData.setViewName("AddItem");
		formData.addObject("form", product);
		return formData;
	}

	@PostMapping(value = "add")
	public String showingForm(@ModelAttribute("form") ProductVoImpl product, HttpServletRequest request)
			throws Exception {
		ProductDtoImpl dtoImpl=Mapper.toGetProductDtoImpl(product);
		service.insertProduct(dtoImpl);
		return "redirect:welcome";
	}

	@GetMapping(value = "deletet")
	public String delete(HttpServletRequest request) throws NumberFormatException, Exception {
		service.deleteRecord(Integer.parseInt(request.getParameter("id")));
		return "redirect:welcome";
	}

	@PostMapping(value = "/search")
	@ExceptionHandler(RecordNotFoundExceptions.class)
	public ModelAndView findProducts(ModelAndView modelAndView, HttpServletRequest request)
			throws RecordNotFoundExceptions {

		List<ProductDtoImpl> list = service.findElementsByCategory(request.getParameter("search"));
		modelAndView.addObject("category", list);
		modelAndView.setViewName("SearchItem");
		return modelAndView;
	}

}
