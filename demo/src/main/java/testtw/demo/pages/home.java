package testtw.demo.pages;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class home {

  @RequestMapping(value="/")
    public static String home_function(){
      return "this is the home page";
    }


    @PostMapping(value="post-tw")
  public static String test_post(){
    return "el post ah sido enviado";
  }


}



