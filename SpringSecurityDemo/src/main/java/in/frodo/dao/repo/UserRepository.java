package in.frodo.dao.repo;

import in.frodo.dao.entity.Role;
import in.frodo.dao.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {

}
