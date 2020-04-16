package jd.matveev.cityGuide;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class CityGuideApplication {

	//todo: move to beanPostProcessor
	//static {ApiContextInitializer.init();}

	@Autowired
	private static Bot bot;

	public static void main(String[] args) {

		SpringApplication.run(CityGuideApplication.class, args);

		bot.runBot();
	}

}
