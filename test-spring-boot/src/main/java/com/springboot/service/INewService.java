package com.springboot.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.springboot.dto.NewDTO;

public interface INewService {

	NewDTO save(NewDTO newDTO);
	void delete(Long[] ids);
	List<NewDTO> findAll(Pageable page);
	int totalItem();
}
