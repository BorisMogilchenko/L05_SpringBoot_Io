package ru.quazar.l05springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.quazar.l05springboot.exceptions.RecordNotFoundException;
import ru.quazar.l05springboot.model.IoStream;
import ru.quazar.l05springboot.repository.IoStreamRepository;

import java.util.List;

@Service
public class StringService {

    public static final Logger LOG = LoggerFactory.getLogger(StringService.class);

    private final IoStreamRepository repository;

    public StringService(IoStreamRepository repository) {
        this.repository = repository;
    }

    public List< IoStream > getStrings() {
        return repository.findAll();
    }

    public IoStream createSubString(IoStream entity) {
        return repository.save(entity);
    }

    public IoStream putSubString(Long id, IoStream entity) {
        IoStream savedString = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Cannot find Record with number: " + id));
        savedString.setTargetString(entity.getTargetString());
        return repository.save(savedString);
    }

    public IoStream changeSubString(Long id, IoStream entity) {
        IoStream savedString = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Cannot find Record with number: " + id));
        savedString.setTargetString(entity.getTargetString());
        return repository.save(savedString);
    }

    public IoStream updateSubString(Long id, IoStream entity) {
        IoStream savedString = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Cannot find Record with number: " + id));
        savedString.setTargetString(entity.getTargetString());
        return repository.save(savedString);
    }

    public String getSubString(Long id) {
        return (id <= repository.count() ? repository.findById(id).get().getTargetString() : repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Cannot find Record with number: " + id)).getTargetString());
        //        return (id <= repository.count() ? repository.findById(id).get().getTargetString() : "Record with number " + id + " is absent in database!!!");
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

    @Override
    public String toString() {
        return "StringService{" +
                "repository=" + repository +
                '}';
    }

}
