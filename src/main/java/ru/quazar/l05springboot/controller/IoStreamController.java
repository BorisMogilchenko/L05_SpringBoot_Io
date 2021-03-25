package ru.quazar.l05springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ru.quazar.l05springboot.model.IoStream;
import ru.quazar.l05springboot.repository.IoStreamRepository;
import ru.quazar.l05springboot.service.StringService;

/**
 * IoStreamController witch manages models, services, repositories.
 *
 * @version $Id: IoStreamController.java,v 1.0 2019-08-20 23:30:42 Exp $
 * @author  <A HREF="mailto:boris.mogilchenko@yandex.ru">Boris Mogilchenko</A>
 */

@Component
@RestController
public class IoStreamController {
//    @Value("${outFileName}")
    private String outFileName = "homework_02_output.txt";

    @Autowired
    private IoStreamRepository repository;

    @Autowired
    private StringService stringService;

    @GetMapping("/")
    public String index() {
        return "Welcome to Spring Boot!";
    }

    @GetMapping("/substring")
    public String getSubString() {

        String responseSubString = repository.findById((Long) repository.count()).get().getTargetString();

        return "Искомая подстрока: " + responseSubString;
    }

    @PostMapping("/poststring")
    IoStream createSubString(@RequestBody String loadString) {
        IoStream ioStream = new IoStream();
        ioStream.setTargetString(loadString);

        return stringService.createSubString(ioStream);
    }

    @PutMapping("putstring/{id}")
    IoStream updateList(@RequestBody String loadString, @PathVariable Long id) {
        IoStream ioStream = new IoStream();
        ioStream.setTargetString(loadString);

        return stringService.updateSubString(id, ioStream);
    }

    @GetMapping("/getstring/{id}")
    String getList(@PathVariable Long id) {

        return stringService.getSubString(id);
    }

    @DeleteMapping("/deleteall")
    void deleteAllLists() {
        StringService stringService = new StringService();
        stringService.deleteAllSubStrings();
    }

    @DeleteMapping("/deletestring/{id}")
    String deleteList(@PathVariable Long id) {
        StringService stringService = new StringService();
        return stringService.deleteSubString(id);
    }
}
