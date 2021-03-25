package ru.quazar.l05springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.quazar.l05springboot.model.IoStream;

import javax.persistence.Table;

/**
 * Interface for making repository of CustomList with parameter T. This parameter extends from class Number.
 *
 * @version $Id: IoStreamRepository.java,v 1.0 2021-02-15 23:30:42 Exp $
 * @author  <A HREF="mailto:boris.mogilchenko@yandex.ru">Boris Mogilchenko</A>
 */

@Repository
@Table(name="Target_String")
public interface IoStreamRepository extends JpaRepository< IoStream, Long> {
//    void save(String target);
}
