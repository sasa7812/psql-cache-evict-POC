package ru.savvy.entity.course;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sasa <a href="mailto:sasa7812@gmail.com">Alexander Nikitin</a>
 */
public class Lesson implements Serializable {
    private String name;
    private Date startsAt;
    private Long length;

    public Lesson() {
    }

    public Lesson(String name, Date startsAt, Long length) {
        this.name = name;
        this.startsAt = startsAt;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(Date startsAt) {
        this.startsAt = startsAt;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }
}
