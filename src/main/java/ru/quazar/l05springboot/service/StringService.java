package ru.quazar.l05springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.quazar.l05springboot.model.IoStream;
import ru.quazar.l05springboot.repository.IoStreamRepository;

import java.util.List;

@Service
public class StringService {
    @Autowired
    private IoStreamRepository repository;

    public List< IoStream > getStrings() {
        return repository.findAll();
    }

    public IoStream createSubString(IoStream entity) {
        return repository.save(entity);
    }

    public IoStream putSubString(Long id, IoStream entity) {
        IoStream savedString = repository.findById(id).get();
        savedString.setTargetString(entity.getTargetString());
        return repository.save(savedString);
    }

    public IoStream updateSubString(Long id, IoStream entity) {
        IoStream savedString = repository.findById(id).get();
        savedString.setTargetString(entity.getTargetString());
        return repository.save(savedString);
    }

    public String getSubString(Long id) {
        return (id <= repository.count() ? repository.findById(id).get().getTargetString() : "Record with number " + id + " is absent in database!!!");
    }

    public void deleteAllSubStrings() {
        repository.deleteAll();
    }

    public String deleteSubString(Long id) {
        if (id <= repository.count() ) {
            repository.deleteById(id);
            return "Record with number " + id + " deleted successfully from database!!!";
        } else {
            return "Record with number " + id + " is absent in database!!!";
        }
    }
}