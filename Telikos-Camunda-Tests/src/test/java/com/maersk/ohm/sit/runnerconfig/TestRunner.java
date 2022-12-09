package com.maersk.ohm.sit.runnerconfig;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        plugin = {"pretty"},
        tags ="@test2",
        glue={"com.maersk.ohm.sit.stepdefinition"},
        publish = true, stepNotifications = true)

public class TestRunner{

}
