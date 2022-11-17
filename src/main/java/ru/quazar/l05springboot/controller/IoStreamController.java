package ru.quazar.l05springboot.controller;

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

    private final IoStreamRepository repository;

    private final StringService stringService;

    public IoStreamController(IoStreamRepository repository, StringService stringService) {
        this.stringService = stringService;
        this.repository = repository;
    }

    @GetMapping("/home")
    public String index() {
        return "Welcome to Spring Boot IO Substring Service!";
    }

    @GetMapping("/")
    public String getStrings() {

        return "Искомые подстроки: " + stringService.getStrings().toString();
    }

    @GetMapping("/substring")
    public String getSubString() {

        String responseSubString = repository.findById((Long) repository.count()).get().getTargetString();

        return "Искомая подстрока: " + responseSubString;
    }

    @GetMapping("/{id}")
    String getList(@PathVariable Long id) {

        return stringService.getSubString(id);
    }

    @PostMapping("/")
    IoStream createSubString(@RequestBody String loadString) {
        IoStream ioStream = new IoStream();
        ioStream.setTargetString(loadString);

        return stringService.createSubString(ioStream);
    }

    @PutMapping("/{id}")
    IoStream updateList(@RequestBody String loadString, @PathVariable Long id) {
        IoStream ioStream = new IoStream();
        ioStream.setTargetString(loadString);

        return stringService.updateSubString(id, ioStream);
    }

    @PatchMapping("/{id}")
    IoStream patchList(@RequestBody String loadString, @PathVariable Long id) {
        IoStream ioStream = new IoStream();
        ioStream.setTargetString(loadString);

        return stringService.changeSubString(id, ioStream);
    }

    @DeleteMapping("/")
    void deleteAllLists() {
        stringService.deleteAllSubStrings();
    }

    @DeleteMapping("/{id}")
    String deleteList(@PathVariable Long id) {
        return stringService.deleteSubString(id);
    }

    @Override
    public String toString() {
        return "IoStreamController{" +
                "repository=" + repository +
                ", stringService=" + stringService +
                '}';
    }

}
