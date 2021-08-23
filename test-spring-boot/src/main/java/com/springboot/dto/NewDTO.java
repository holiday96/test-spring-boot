package com.springboot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewDTO extends AbstractDTO<NewDTO>{

	private String title;
	private String content;
	private String shortDescription;
	private String categoryCode;
	private String thumbnail;
}
