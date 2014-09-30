package ru.savvy.entity;

import ru.savvy.entity.course.CourseMapped;
import ru.savvy.entity.course.CourseMappedConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;

/**
 * Entity containing POJO as a member to be mapped into Postgres's json
 *
 * @author sasa <a href="mailto:sasa7812@gmail.com">Alexander Nikitin</a>
 */

@Entity
public class Course extends AbstractEntity {
    @Column(name = "course_mapped", columnDefinition = "json")
    @Convert(converter = CourseMappedConverter.class) // all the magic has gone to converter class
    private CourseMapped courseMapped;

    public CourseMapped getCourseMapped() {
        return courseMapped;
    }

    public void setCourseMapped(CourseMapped courseMapped) {
        this.courseMapped = courseMapped;
    }
}
