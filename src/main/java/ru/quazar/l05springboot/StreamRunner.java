package ru.quazar.l05springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.quazar.l05springboot.config.ConfigProperties;
import ru.quazar.l05springboot.model.IoStream;
import ru.quazar.l05springboot.repository.IoStreamRepository;
import ru.quazar.l05springboot.service.IoStreamService;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ComponentScan("ru.quazar.l05springboot")
@EnableConfigurationProperties(ConfigProperties.class)
@ConfigurationPropertiesScan("ru.quazar.l05springboot.config")
@EntityScan("ru.quazar.l05springboot.model")
public class StreamRunner implements CommandLineRunner {

//    @Value("${inFileName}")
//    private String inFileName;

    private static final Logger logger = LoggerFactory.getLogger(IoStreamApplication.class);
//    private AtomicLong entityId = new AtomicLong(0L);
//    private Long entityId = 0L;

    @Autowired
    private IoStreamRepository repository;

    @Autowired
    private ConfigProperties configProperties;

    private String inFileName;

    @PostConstruct
    public void init() {
        inFileName = configProperties.getInFileName();
    }

//    public StreamRunner(ConfigProperties configProperties) {
//        this.configProperties = configProperties;
//    }

    @Override
    public void run(String... args) throws IOException {
        String targetString;
        String loadFilePath = "";

        List<String> params = Arrays.stream(args)
        .collect(Collectors.toList());

        if (params.size() > 0) {
            System.out.println( "" );
            System.out.println( "Первый параметр: " + params.get( 0 ) );
            System.out.println( "" );
            System.out.println( "Второй параметр: " + params.get( 1 ) );
            System.out.println( "" );
        } else {
            System.out.println("");
            System.out.println("No arguments!!!");
            System.out.println("Enter, please!!!");
            System.out.println("");
        }

        if (wouldVerifyArguments(params)) {
            switch (params.size()) {
                case 1:
                    loadFilePath = "";
                    break;
                case 2:
                    loadFilePath = params.get(1);
                    break;
                default:
                    throw new NumberFormatException("Incorrect arguments!!!");
            }

            IoStreamService ioStreamService = new IoStreamService();

            File inputFile = ioStreamService.getFileWithConditions(args[0], loadFilePath, inFileName);
//            FileToStreamService fileToStream = new FileToStreamService();
            targetString = ioStreamService.loadFileToStream(inputFile);
            IoStream ioStream = new IoStream();
            ioStream.setTargetString(targetString);
            repository.save(ioStream);
            System.out.println("");
            System.out.println("Число записей в БД: " + repository.count());
            System.out.println("");
        } else {
            logger.warn("Invalid arguments " + params.toString());
        }
    }

    private static boolean wouldVerifyArguments(List<String> args) {
        boolean success = false;

        if (args.size() > 0) {
        success = true;
        } else {
            throw new RuntimeException("No arguments!!!");
        }

        return success;
    }
}