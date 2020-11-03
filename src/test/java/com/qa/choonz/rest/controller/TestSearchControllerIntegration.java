package com.qa.choonz.rest.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestSearchControllerIntegration {
	
	@Autowired
	private SearchController controller;
	
	@Autowired
	private ModelMapper modelMapper;
	
	

}
