package jschool2016.translator.app;

import jschool2016.translator.Conf;
import org.springframework.boot.SpringApplication;

public class Launcher {
    public static void main(String[] args) {
//        new File(PairController.uploadingdir).mkdirs();
        SpringApplication.run(Conf.class);
    }
}
