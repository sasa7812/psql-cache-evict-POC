package ru.savvy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.savvy.entity.UserProfile;

/**
 * @author sasa <a href="mailto:sasa7812@gmail.com">Alexander Nikitin</a>
 */
@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile,Long>{
}
