package work.liudayu.wzsb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class WzsbApplication {

    public static void main(String[] args) {
        SpringApplication.run(WzsbApplication.class, args);
    }

    @RequestMapping(value = "/index")
    String image(Model model){
        return "index";
    }
}
