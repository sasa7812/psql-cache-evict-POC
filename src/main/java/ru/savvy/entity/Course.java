package ru.savvy.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.persistence.annotations.ChangeTracking;
import org.eclipse.persistence.annotations.ChangeTrackingType;
import org.eclipse.persistence.descriptors.changetracking.ChangeTracker;
import ru.savvy.entity.course.CourseMapped;
import ru.savvy.entity.course.CourseMappedConverter;

import javax.persistence.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

/**
 * Entity containing POJO as a member to be mapped into Postgres's json
 *
 * @author sasa <a href="mailto:sasa7812@gmail.com">Alexander Nikitin</a>
 */

@Entity
@ChangeTracking(ChangeTrackingType.DEFERRED)
public class Course extends AbstractEntity {
    @Column(name = "course_mapped", columnDefinition = "json")
    @Convert(converter = CourseMappedConverter.class) // all the magic has gone to converter class
    private String courseMapped;

    @Column(insertable = false, updatable = false)
    private CourseMapped cachedCourseObject;


    /**
     * Here we make json from our object and persist it into DB
     */
    @PrePersist
    @PreUpdate
    protected void prePersist() {
        System.err.println(" --- Prepersist");
        this.courseMapped = jsonify(cachedCourseObject);
    }

    private String jsonify(CourseMapped cm) {
        String json;
        try {
            json = new ObjectMapper().writeValueAsString(cm);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }


    public CourseMapped getCourseMapped() {
        if (cachedCourseObject == null) {
            try {
                cachedCourseObject = new ObjectMapper().readValue(courseMapped, CourseMapped.class);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return cachedCourseObject;
    }

    public void setCourseMapped(CourseMapped courseMapped) {
        cachedCourseObject = courseMapped;
    }

}
