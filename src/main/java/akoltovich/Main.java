package akoltovich;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages =
        {"akoltovich/repository"
                , "akoltovich/service"
                , "akoltovich/handler"
                , "akoltovich/router"
        })
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
