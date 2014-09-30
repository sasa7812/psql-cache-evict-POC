package ru.savvy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.savvy.entity.Course;

/**
 * @author sasa <a href="mailto:sasa7812@gmail.com">Alexander Nikitin</a>
 */

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
}
