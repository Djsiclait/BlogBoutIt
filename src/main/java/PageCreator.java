import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

/**
 * Created by Eduardo veras on 01-Jun-16.
 */
public class PageCreator {

    public PageCreator()
    {
        get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "Welcome.");
            return new ModelAndView(attributes, "index.ftl");

        }, new FreeMarkerEngine());


        get("/login", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "Welcome.");
            return new ModelAndView(attributes, "login.ftl");

        }, new FreeMarkerEngine());

        get("/register", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "Welcome.");
            return new ModelAndView(attributes, "registerPage.ftl");
        }, new FreeMarkerEngine());
    }
}
