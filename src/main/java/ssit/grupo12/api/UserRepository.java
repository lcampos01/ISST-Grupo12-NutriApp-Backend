package ssit.grupo12.api;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmailAndPassword(String email,String password);
}
