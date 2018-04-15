package com.chb.face;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class FaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FaceApplication.class, args);
//		AuthService.getAuth();
//		FaceUtil.add("chb","陈焕斌","chbljc");
		FaceUtil.identify();
	}
}
