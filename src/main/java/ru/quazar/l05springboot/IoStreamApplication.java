package ru.quazar.l05springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.quazar.l05springboot.config.ConfigProperties;

/**
 * Put file "homework_02_input.txt" in any path of file system, then put into
 * application resource folder.
 * Make class for finding source file by path, find substring "Hello, guys!".
 * Write to the file "homework_02_output.txt" twenty symbols behind and
 * twenty symbols after finding substring.
 *
 * @version $Id: FileToStream.java,v 1.0 2021-02-15 23:30:42 Exp $
 * @author  <A HREF="mailto:boris.mogilchenko@yandex.ru">Boris Mogilchenko</A>
 */

@SpringBootApplication
@EnableConfigurationProperties(ConfigProperties.class)
@ConfigurationPropertiesScan("ru.quazar.l05springboot.config")
@EnableJpaRepositories(basePackages = "")
@EnableTransactionManagement
@EntityScan(basePackages="")
public class IoStreamApplication {
    public static void main(String[] args) {
        SpringApplication.run(IoStreamApplication.class, args);
    }
}
