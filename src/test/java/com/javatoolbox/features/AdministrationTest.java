package com.javatoolbox.features;

import com.javatoolbox.ToolboxApplication;
import net.codestory.simplelenium.SeleniumTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ToolboxApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:9000")
public class AdministrationTest extends SeleniumTest {
    @Test
    public void addingContent() {
        addGroup();
        addCategory();
        addProject();
    }

    private void addGroup() {
        goToAdmin();

        find("a").withText("New Group").click();
        find("#name").fill("HTML and Markup");
        find("input[type=submit]").click();
        find("h1").should().contain("HTML and Markup");

        find("a").withText("Projects").first().click();
        find("body").should().not().contain("HTML and Markup");
    }

    private SeleniumTest goToAdmin() {
        return goTo(getDefaultBaseUrl() + "/admin");
    }

    private void addCategory() {
        goToAdmin();

        find("a").withText("New Category").click();
        find("#name").fill("Markup Processors");
        find("#groupId").select("HTML and Markup");
        find("input[type=submit]").click();
        find("h1").should().contain("Markup Processors");

        find("a").withText("Projects").first().click();
        find("body").should().not().contain("Markup Processors");
    }

    private void addProject() {
        goToAdmin();

        find("a").withText("New Project").click();
        find("#name").fill("Java Toolbox");
        find("#description").fill("A website for discovering Java libraries and tools");
        find("#categoryId").select("Markup Processors");
        find("#websiteUrl").fill("http://www.java-toolbox.com/");
        find("#sourcecodeUrl").fill("https://github.com/TheJavaToolbox/java-toolbox");
        find("#issuesUrl").fill("https://github.com/TheJavaToolbox/java-toolbox/issues");
        find("#groupId").fill("org.spilth");
        find("#artifactId").fill("blog");
        find("input[type=submit]").click();

        find("h1").should().contain("Java Toolbox");
        find("body").should().contain("Website");
        find("body").should().contain("Source Code");
        find("body").should().contain("Bug Tracker");

        find("a").withText("Projects").first().click();
        find("body").should().contain("HTML and Markup");
//        find("body").should().contain("Markup Processors");
    }

    @Override
    protected String getDefaultBaseUrl() {
        return "http://localhost:9000";
    }
}
