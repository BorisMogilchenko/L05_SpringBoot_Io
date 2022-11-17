package ru.quazar.l05springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.quazar.l05springboot.config.AppConfig;
import ru.quazar.l05springboot.config.EnvironmentProperties;
import ru.quazar.l05springboot.model.IoStream;
import ru.quazar.l05springboot.repository.IoStreamRepository;
import ru.quazar.l05springboot.service.IoStreamService;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ComponentScan("ru.quazar.l05springboot")
@EnableConfigurationProperties(AppConfig.class)
@EntityScan("ru.quazar.l05springboot.model")
public class StreamRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(IoStreamApplication.class);
//    private AtomicLong entityId = new AtomicLong(0L);
//    private Long entityId = 0L;

    private final String findSubString;

    private final String inputFileName;

    private final String outputFileName;

    private final EnvironmentProperties environmentProperties;

    private final IoStreamRepository repository;

    private final AppConfig appConfig;

    public StreamRunner(AppConfig appConfig, EnvironmentProperties environmentProperties, IoStreamRepository repository) {
        this.findSubString = environmentProperties.getFindString();
        this.inputFileName = environmentProperties.getInFileName();
        this.outputFileName = environmentProperties.getOutFileName();
        this.environmentProperties = environmentProperties;
        this.appConfig = appConfig;
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws IOException {
        String targetString;
        String loadFilePath = "";

        List<String> params = Arrays.stream(args)
        .collect(Collectors.toList());

        System.out.println("");
        System.out.println("Параметры 1 конфига: " + appConfig.initEnvProperties());
        System.out.println("");
        System.out.println("Параметры 1 окружения: " + findSubString);
        System.out.println("");
        System.out.println("Параметры 2 окружения: " + inputFileName);
        System.out.println("");
        System.out.println("Параметры 3 окружения: " + outputFileName);

//        if (params.size() > 0) {
//            System.out.println( "" );
//            System.out.println( "Первый параметр: " + params.get( 0 ) );
//            System.out.println( "" );
//            System.out.println( "Второй параметр: " + params.get( 1 ) );
//            System.out.println( "" );
//            System.out.println( "Размеры 1 файлов: " + appConfig.toString().length() );
//            System.out.println( "" );
//            System.out.println( "Искомая подстрока: " + (findSubString.length() > 0 ? findSubString : "Пусто!!!") );
//            System.out.println( "" );
//            System.out.println( "Имя входящее файла: " + (inputFileName.length() > 0 ? inputFileName : "Пусто!!!") );
//            System.out.println( "" );
//            System.out.println( "Имя исходящее файла: " + (outputFileName.length() > 0 ? outputFileName : "Пусто!!!") );
//            System.out.println( "" );
//            System.out.println( "Размеры 2 мапы: " + appConfig.initEnvProperties().size() );
//            System.out.println( "" );
//            System.out.println( "Параметры 2 файлов: " + appConfig.initEnvProperties().toString() );
//            System.out.println( "" );
//            System.out.println( "Искомая подстрока 2: " + (appConfig.initEnvProperties().get("findString").length() > 0 ? appConfig.initEnvProperties().get("findString") : "Пусто!!!") );
//            System.out.println( "" );
//            System.out.println( "Имя входящее файла: " + (appConfig.initEnvProperties().get("inputfilename").length() > 0 ? appConfig.initEnvProperties().get("inputfilename") : "Пусто!!!") );
//            System.out.println( "" );
//            System.out.println( "Имя исходящее файла: " + (appConfig.initEnvProperties().get("outputfilename").length() > 0 ? appConfig.initEnvProperties().get("outputfilename") : "Пусто!!!") );
//            System.out.println( "" );
//        } else {
//            System.out.println("");
//            System.out.println("No arguments!!!");
//            System.out.println("Enter, please!!!");
//            System.out.println("");
//        }

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

            IoStreamService ioStreamService = new IoStreamService(appConfig, environmentProperties);

            File inputFile = ioStreamService.getFileWithConditions(args[0], loadFilePath, inputFileName);
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

    @Override
    public String toString() {
        return "StreamRunner{" +
                "findSubString='" + findSubString + '\'' +
                ", inputFileName='" + inputFileName + '\'' +
                ", outputFileName='" + outputFileName + '\'' +
                ", environmentProperties=" + environmentProperties +
                ", repository=" + repository +
                ", appConfig=" + appConfig +
                '}';
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