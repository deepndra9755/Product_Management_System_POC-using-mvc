package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.example.controller.Exceptions.RecordNotFoundExceptions;
import com.example.controller.mapper.Mapper;
import com.example.dto.ProductDtoImpl;
import com.example.service.IProductManagement;
import com.example.vo.ProductVoImpl;

@Controller
public class ProductImpl {

	@Autowired
	private IProductManagement service;

	@GetMapping(value = "welcome")
	public ModelAndView showHome() {
	return showPages("1");
	}

	@GetMapping(value = "/page/{pageNum}")
	public ModelAndView showPages(@PathVariable(name = "pageNum") String pageNum) {
		ModelAndView modelAndView = new ModelAndView();
		Pageable pageable = null;
		int value = Integer.parseInt(pageNum);
		pageable = PageRequest.of(value - 1, 10, Sort.by(Order.asc("name")));
		List<ProductVoImpl> retriveList = Mapper.toGetProductVoList(service.findAllRecords(pageable));
		modelAndView.addObject("currentPage", pageNum);
		modelAndView.addObject("values", retriveList);
		modelAndView.addObject("totalRecord", service.getAllRecodsCount(pageable));
		modelAndView.addObject("totalPages", service.getTotalPages(pageable));
		modelAndView.setViewName("Home");
		return modelAndView;
	}

	@GetMapping(value = "add")
	public ModelAndView addMore(@ModelAttribute("form") ProductVoImpl product, HttpServletRequest request,
			ModelAndView formData, @RequestParam(name = "id", required = false) String pid)
			throws RecordNotFoundExceptions, Exception {
		Integer id = Integer.parseInt(pid);
		if (id != 0 && id != null) {
			ProductDtoImpl proDtoImpl = service.findByID(id);
			proDtoImpl.setId(id);
			ProductVoImpl voImpl = Mapper.toGetProductVo(proDtoImpl);
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
		ProductDtoImpl dtoImpl = Mapper.toGetProductDtoImpl(product);
		dtoImpl.setId(product.getId());
		service.insertProduct(dtoImpl);
		return "redirect:welcome";
	}

	@GetMapping(value = "deletet")
	public String delete(HttpServletRequest request, @RequestParam(required = false) String id)
			throws  RecordNotFoundExceptions,Exception {
		service.deleteRecord(Integer.parseInt(id));
		return "redirect:welcome";
	}

	@PostMapping(value = "search")
	public ModelAndView findProducts(ModelAndView modelAndView, HttpServletRequest request) throws RecordNotFoundExceptions,Exception {
		String productName = request.getParameter("pname");
		Float price = Float.parseFloat(request.getParameter("price"));
		Float to = Float.parseFloat(request.getParameter("to"));

		List<ProductDtoImpl> list = service.findProduct(productName, price, to);
		List<ProductVoImpl> voImpls = Mapper.toGetProductVoList(list);
		modelAndView.addObject("category", voImpls);
		modelAndView.setViewName("SearchItem");
		return modelAndView;
	}

}
