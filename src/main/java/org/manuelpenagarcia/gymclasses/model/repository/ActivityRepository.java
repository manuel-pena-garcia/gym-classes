package org.manuelpenagarcia.gymclasses.model.repository;

import org.manuelpenagarcia.gymclasses.model.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

}
