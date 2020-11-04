package com.qa.choonz.cuke;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/cuke",
		
plugin = {"pretty", "html:target/reports/htmlReports",
		"json:target/reports/json/demositereport.json", "junit:target/reports/xml/xmlReports"},
monochrome = true
		)
public class testRunner {

}
