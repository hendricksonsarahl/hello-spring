package com.example.hellospring.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/* Move createMessage to it's own class file OUTSIDE of the controllers directory
* */

@Controller
public class HelloController {

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    @ResponseBody
    public String helloForm(){
        String html = "<form method='post'>" +
                "<input type='text' name='name' />" +
                "</form>" +"<select name='greeting'>" +
                "<option value='English'>English</option>" +
                "<option value='Spanish'>Spanish</option>" +
                "<option value='French'>French</option>" +
                "<option value='German'>German</option>" +
                "<option value='Klingon'>Klingon</option>" +
                "</select>" +
                "<input type='submit' value='Greet Me!'/>" +
                "</form>";
        return html;
    }

        public static String createMessage(String lang) {

            String greeting = "";
            if(lang.equals("English")){
                greeting = "Hello ";
            } else if (lang.equals("Spanish")){
                greeting = "Hola ";
            } else if (lang.equals("French")){
                greeting = "Bonjoir ";
            } else if (lang.equals("German")){
                greeting = "Hallo ";
            } else if (lang.equals("Klingon")) {
                greeting = "There is no Klingon word for 'Hello', ";
            }
            return greeting;
        }

    @RequestMapping(value = "hello", method = RequestMethod.POST)
    @ResponseBody
    public String helloPost(HttpServletRequest request){
        String name = request.getParameter("name");
        String greeting = request.getParameter("greeting");
        String message = createMessage(greeting);
        return name + message;

    }

    @RequestMapping(value = "hello/{name}")
    @ResponseBody
    public String helloUrlSegment(@PathVariable String name){
        return "Hello " + name;
    }

    @RequestMapping(value = "")
    @ResponseBody
    public String index(HttpServletRequest request){

        String name = request.getParameter("name");

        if (name == null) {
            name = "World";
        }
        return "Hello " + name;
    }

    @RequestMapping(value = "goodbye")
    public String goodbye() {
        return "redirect:/";
    }

}