package com.qa.choonz.rest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.qa.choonz.persistence.domain.Artist;


public class SearchController {

	@RequestMapping(value="/search/{searchTerm}")
	public ModelAndView Search(@RequestParam(value = "searchTerm", required = false) String pSearchTerm, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("search");
		
		mav.addObject("searchTerm", pSearchTerm);
		mav.addObject("searchResult", Artist.findByName(pSearchTerm));
		
		return mav;
	}
}
