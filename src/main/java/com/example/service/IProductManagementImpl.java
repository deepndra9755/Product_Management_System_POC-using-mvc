package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.example.bo.Product;
import com.example.controller.Exceptions.RecordNotFoundExceptions;
import com.example.dao.IProductRepo;
import com.example.dto.ProductDtoImpl;
import com.example.service.mapper.Mapper;

@Service
public class IProductManagementImpl implements IProductManagement {

	@Autowired
	private IProductRepo repo;

	public ProductDtoImpl insertProduct(ProductDtoImpl dto) throws Exception {
		// TODO Auto-generated method stub
		try {
			Product persistence = Mapper.toGetProduct(dto);
			Product persistent = repo.save(persistence);
			if (persistent.getId() > 0) {
				return Mapper.toGetProductDtoImpl(persistent);
			}
			throw new RuntimeException(" Internal error record does't inserted ");
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	@Override
	public void deleteRecord(Integer id) throws RecordNotFoundExceptions, Exception {
		// TODO Auto-generated method stub
		try {
			Optional<Product> record = repo.findById(id);
			if (record.isPresent()) {
				repo.deleteById(id);
				return;
			}
			throw new RecordNotFoundExceptions();
		} catch (RecordNotFoundExceptions e) {
			// TODO: handle exception
			throw e;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}

	}

	@Override
	public List<ProductDtoImpl> findAllRecords() throws RecordNotFoundExceptions, Exception {
		// TODO Auto-generated method stub
		try {
			List<Product> retiveProductList = repo.findAll();
			if (!retiveProductList.isEmpty())
				return Mapper.toGetProductDtoList(retiveProductList);
			else
				throw new RecordNotFoundExceptions();

		} catch (RecordNotFoundExceptions e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<ProductDtoImpl> findAllRecords(Pageable page) {
		// TODO Auto-generated method stub
		try {
			Page<Product> page1 = repo.findAll(page);
			List<Product> retiveProductList = page1.getContent();
			if (!retiveProductList.isEmpty()) {
				return Mapper.toGetProductDtoList(retiveProductList);
			}
			throw new RecordNotFoundExceptions();
		} catch (RecordNotFoundExceptions e) {
			// TODO: handle exception
			throw e;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	@Override
	public ProductDtoImpl findByID(Integer id) throws RecordNotFoundExceptions, Exception {
		// TODO Auto-generated method stub
		try {
			Optional<Product> optional = repo.findById(id);
			if (optional.isPresent()) {
				return Mapper.toGetProductDtoImpl(optional.get());
			}
			throw new RecordNotFoundExceptions();

		} catch (RecordNotFoundExceptions e) {
			// TODO: handle exception
			throw e;
		}

		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	@Override
	public void throwIfNotExist(Integer id) throws Exception {
		// TODO Auto-generated method stub
		if (!repo.findById(id).isEmpty()) {

			throw new RecordNotFoundExceptions("Given Id Based Record Not Present");
		}
		return;
	}

	@Override
	public Long getAllRecodsCount(Pageable page) {
		// TODO Auto-generated method stub
		return (long) repo.findAll(page).getTotalElements();

	}

	@Override
	public Long getTotalPages(Pageable page) {
		// TODO Auto-generated method stub
		return (long) repo.findAll(page).getTotalPages();

	}

	@Override
	public List<ProductDtoImpl> findElementsByCategory(String category) throws RecordNotFoundExceptions {
		// TODO Auto-generated method stub

		List<Product> retrivalItems = repo.findByCategory(category);
		if (!retrivalItems.isEmpty()) {
			return Mapper.toGetProductDtoList(retrivalItems);
		}
		throw new RecordNotFoundExceptions();
	}

	@Override
	public List<ProductDtoImpl> findAllRecords(Integer start, String sortBy) throws Exception {
		// TODO Auto-generated method stub
		try {
			Pageable pageable = null;
			pageable = PageRequest.of(start - 1, 10, Sort.by(Order.asc(sortBy)));
			Page<Product> page = repo.findAll(pageable);
			if (!page.isEmpty()) {
				List<Product> productList = page.getContent();
				return Mapper.toGetProductDtoList(productList);
			}
			throw new RecordNotFoundExceptions();

		} catch (RecordNotFoundExceptions e) {
			// TODO: handle exception
			throw e;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	@Override
	public List<ProductDtoImpl> findProduct(String name, Float price, Float to)
			throws RecordNotFoundExceptions, Exception {
		// TODO Auto-generated method stub
		try {
			List<Product> retriveList = repo.findProduct(name.toUpperCase(), price, to);
			if (!retriveList.isEmpty())
				return Mapper.toGetProductDtoList(retriveList);
			else
				throw new RecordNotFoundExceptions("msg");
		} catch (RecordNotFoundExceptions e) {
			// TODO: handle exception
			throw e;
		}

	}
}
