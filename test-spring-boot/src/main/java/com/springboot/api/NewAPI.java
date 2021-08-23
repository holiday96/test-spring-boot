package com.springboot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.api.output.NewOutput;
import com.springboot.dto.NewDTO;
import com.springboot.service.INewService;

@CrossOrigin
@RestController
public class NewAPI {

	@Autowired
	private INewService newService;

	@GetMapping("/new")
	public NewOutput showNew(	@RequestParam(defaultValue = "1") Integer page,
								@RequestParam(defaultValue = "2") Integer limit) {
		NewOutput result = new NewOutput();
		result.setPage(page);
		Pageable pageable = PageRequest.of(page - 1, limit);
		result.setListResult(newService.findAll(pageable));
		result.setTotalPage((int) Math.ceil((double) newService.totalItem() / limit));
		return result;
	}

	@PostMapping("/new")
	public NewDTO createNew(@RequestBody NewDTO model) {
		return newService.save(model);
	}

	@PutMapping("/new/{id}")
	public NewDTO updateNew(@RequestBody NewDTO model, @PathVariable("id") Long id) {
		model.setId(id);
		return newService.save(model);
	}

	@DeleteMapping("/new")
	public void deleteNew(@RequestBody Long[] ids) {
		newService.delete(ids);
	}
}
