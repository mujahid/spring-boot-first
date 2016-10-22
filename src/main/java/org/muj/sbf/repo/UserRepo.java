package org.muj.sbf.repo;

import org.muj.sbf.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long>, UserRepoCustom {

}
