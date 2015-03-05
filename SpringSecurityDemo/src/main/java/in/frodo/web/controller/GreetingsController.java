package in.frodo.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GreetingsController {
  
  @RequestMapping("greetings")
  public String getGreetings(){
    System.out.println("Controller: Received Request for Greetings");
    return "Welcome";
  }
}
