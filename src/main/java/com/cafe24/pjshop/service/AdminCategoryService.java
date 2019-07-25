package com.cafe24.pjshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.pjshop.repository.CategoryDao;
import com.cafe24.pjshop.vo.CategoryVo;

@Service
public class AdminCategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	public int addTopCategory(CategoryVo categoryVo) {
		// TODO Auto-generated method stub
		return categoryDao.addTopCategory(categoryVo);
	}

	public int checkName(String name) {
		// TODO Auto-generated method stub
		return categoryDao.checkName(name);
	}

	public List<CategoryVo> getList(Optional<Integer> groupNo) {
		// TODO Auto-generated method stub
		if(groupNo.isPresent()) {
			//상위 카테고리를 선택했을때 하위 카테고리 getList
			return categoryDao.getList(groupNo.get());
		}
		else {
			//제일 상위 카테고리 getList
			return categoryDao.getList(0);
		}
	}

}
