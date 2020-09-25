package org.manuelpenagarcia.gymclasses.model.repository;

import org.manuelpenagarcia.gymclasses.model.entity.ActivityMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityMemberRepository extends JpaRepository<ActivityMember, Long> {

}
