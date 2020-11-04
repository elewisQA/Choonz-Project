package com.qa.choonz.cuke;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/cuke",
		glue = "src/test/java/com.qa.choonz/cuke/stepdefs",
// normally would be a glue link as well
// but due to package structure here its not needed
plugin = {"pretty", "html:target/reports/htmlReports",
			"json:target/reports/json/artistreport.json", "junit:target/reports/xml/xmlReports"},
monochrome = true
)
public class testRunner {

}
