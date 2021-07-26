package ru.quazar.l05springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

/**
 * Model IoStream witch links controller with services, repositories.
 *
 * @version $Id: IoStream.java,v 1.0 2019-08-20 23:30:42 Exp $
 * @author  <A HREF="mailto:boris.mogilchenko@yandex.ru">Boris Mogilchenko</A>
 */

@Data
@AllArgsConstructor
@Entity
public class IoStream {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

//    @OneToOne()
//    @Column(name = "target")
    private String targetString;

    public IoStream() {
    }

    public IoStream(String target) {
        this.targetString = target;
    }

    public String getTargetString() {
        return targetString;
    }

    public void setTargetString(String target) {
        this.targetString = target;
    }

    @Override
    public String toString() {
        return "IoStream{" +
                "id=" + id +
                ", targetString='" + targetString + '\'' +
                '}';
    }
}
