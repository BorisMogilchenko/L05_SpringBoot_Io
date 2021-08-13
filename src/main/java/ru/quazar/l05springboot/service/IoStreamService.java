package ru.quazar.l05springboot.service;

import com.google.common.annotations.VisibleForTesting;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.quazar.l05springboot.config.AppConfig;

import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

@Data
@Component
@ComponentScan("ru.quazar.l05springboot")
@EnableConfigurationProperties(AppConfig.class)
@Service
public class IoStreamService {

    @Autowired
    private AppConfig appConfig;

    /**
     * Get file from source.
     *
     * @param inputFile Object File with path to file
     *
     * @return String file path
     *
     * @throws IOException
     * @exception RuntimeException
     */

    public String loadFileToStream(File inputFile) throws IOException {

        String myStringToFile = "";

        System.out.println("");
        System.out.println("Параметры 1 файлов: " + appConfig);
        System.out.println("");

        System.out.println( "" );
        System.out.println( "Искомая подстрока 1 сервиса!!!: " + (appConfig.getFindString().length() > 0 ? appConfig.getFindString() : "Пусто!!!") );
        System.out.println( "" );

        try (FileInputStream inFile = new FileInputStream(inputFile)

        ) {
            myStringToFile = fileFromStreamToString(inFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return myStringToFile;
    }

    /**
     * Get file from stream to string.
     *
     * @param inFile FileInputStream stream
     *
     * @return cTargetString String for write to file
     *
     * @throws IOException
     * @exception RuntimeException
     */
    @VisibleForTesting
    private String fileFromStreamToString(FileInputStream inFile) throws IOException {
        String cTargetString = "";
        int c;
        String sInputFile;

        StringBuilder inCharToString = new StringBuilder();
        while ((c = inFile.read()) != -1) {
            inCharToString.append(Character.toChars(c));
        }
        cTargetString = findSubstring(inCharToString.toString());

        return cTargetString;
    }

    /**
     * Find target substring from source string.
     *
     * @param cSourceString Source string
     *
     * @return cFindString Finding Substring
     *
     */
    @VisibleForTesting
    private String findSubstring(String cSourceString) {
        String cOutSubString1 = "";
        String cOutSubString2 = "";
        String cFindString = "";
        @NotBlank
//        String findSubString = subString;
        int iBeginFindString;
        int iLenSubString = 20;

        System.out.println( "" );
        System.out.println( "Искомая подстрока 2 сервиса!!!: " + (appConfig.getFindString().length() > 0 ? appConfig.getFindString() : "Пусто!!!") );
        System.out.println( "" );

        if (cSourceString.contains(appConfig.getFindString())) {
            if ((iBeginFindString = cSourceString.indexOf(appConfig.getFindString())) != -1) {
                cOutSubString1 = cSourceString.substring(iBeginFindString - iLenSubString, iBeginFindString);
                cOutSubString2 = cSourceString.substring(iBeginFindString + appConfig.getFindString().length(), iBeginFindString + (appConfig.getFindString().length()) + (iLenSubString));
            }
        }

        cFindString = cOutSubString1 + cOutSubString2;

        return cFindString;
    }

    /**
     * Get file with input conditions.
     *
     * @param fileName Source file name
     * @param filePath Source file path
     * @throws IOException
     * @exception RuntimeException
     */
    public File getFileWithConditions(String target, String filePath, String fileName) throws IOException {
        switch (target) {
            case "1":
                return getFileByPath(filePath, fileName);
            case "2":
                return getFileFromResources(fileName);
            default:
                throw new RuntimeException("Not correct first argument");
        }
    }

    /**
     * Get source file from path
     *
     * @return File by path.
     */

    @VisibleForTesting
    private File getFileByPath(String filePath, String fileName) {
        return new File(filePath, fileName);
    }

    /**
     * Get source file from resources
     *
     * @return File from resources.
     */

    @VisibleForTesting
    private File getFileFromResources(String fileName) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IOException("file is not found!");
        } else {
//            System.out.println("Ресурсный файл: " + new File(resource.getFile()).toString());
//            System.out.println("");
            return new File(resource.getFile());
        }
    }

    /**
     * Put file to target directory.
     *
     * @param myList Collection of strings for upload into file
     * @param target Upload file name
     * @throws IOException
     * @exception RuntimeException
     */

    public void uploadStreamToFile(String outString, File target) throws IOException {

        try(FileOutputStream outFile = new FileOutputStream(target)

        ){
            byte[]buffer = outString.getBytes();
            outFile.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}