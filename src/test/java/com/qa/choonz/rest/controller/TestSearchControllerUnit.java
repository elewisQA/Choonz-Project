package com.qa.choonz.rest.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
class TestSearchControllerUnit {
	
	@Autowired
	private SearchController controller;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	
	

}
