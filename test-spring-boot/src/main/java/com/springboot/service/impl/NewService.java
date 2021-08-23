package com.springboot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.converter.NewConverter;
import com.springboot.dto.NewDTO;
import com.springboot.entity.CategoryEntity;
import com.springboot.entity.NewEntity;
import com.springboot.repository.CategoryRepository;
import com.springboot.repository.NewRepository;
import com.springboot.service.INewService;

@Service
public class NewService implements INewService {

	@Autowired
	private NewRepository newRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private NewConverter newConverter;

	@Override
	public NewDTO save(NewDTO newDTO) {
		NewEntity newEntity = newConverter.toEntity(newDTO);
		if (newDTO.getId() != null) {
			NewEntity oldNewEntity = newRepository.getById(newDTO.getId());
			newEntity = newConverter.toEntity(newDTO, oldNewEntity);
		} else {
			newEntity = newConverter.toEntity(newDTO);
		}
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(newDTO.getCategoryCode());
		newEntity.setCategory(categoryEntity);
		newEntity = newRepository.save(newEntity);
		return newConverter.toDTO(newEntity);
	}

	@Override
	public void delete(Long[] ids) {
		for (Long item : ids) {
			newRepository.deleteById(item);
		}
	}

	@Override
	public List<NewDTO> findAll(Pageable pageable) {
		List<NewDTO> result = new ArrayList<>();
		newRepository.findAll(pageable).getContent().forEach(item -> {
			NewDTO newDTO = newConverter.toDTO(item);
			result.add(newDTO);
		});
		return result;
	}

	@Override
	public int totalItem() {
		return (int) newRepository.count();
	}

}
