package in.frodo.dao.repo;

import in.frodo.dao.entity.AuthenticationToken;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationTokenRepository extends JpaRepository<AuthenticationToken, Long>{
  
  public AuthenticationToken findOneByToken(String token);

}
