package com.springboot.api.output;

import java.util.ArrayList;
import java.util.List;

import com.springboot.dto.NewDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewOutput {

	private int page;
	private int totalPage;
	private List<NewDTO> listResult = new ArrayList<>();
}
