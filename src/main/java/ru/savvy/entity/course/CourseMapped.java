package ru.savvy.entity.course;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Base class for course structure which is mapped as JSON to Course entity
 * Pretty simple 'cause it's just a POC
 * @author sasa <a href="mailto:sasa7812@gmail.com">Alexander Nikitin</a>
 */
public class CourseMapped implements Serializable {
    private String name;
    private Date beginsAt;
    private Date endsAt;
    private List<Level> levels;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBeginsAt() {
        return beginsAt;
    }

    public void setBeginsAt(Date beginsAt) {
        this.beginsAt = beginsAt;
    }

    public Date getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(Date endsAt) {
        this.endsAt = endsAt;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }
}
