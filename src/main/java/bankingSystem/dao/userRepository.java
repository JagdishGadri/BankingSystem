package bankingSystem.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import bankingSystem.entity.user;

public interface userRepository extends JpaRepository<user, Integer> {

	@Query(value = "SELECT u FROM user u")
	List<user> findAllUsers();

	@Query("from user as c where c.id=:id")
	user findUserByUser(@Param("id") int id);

	//@Query("from Contact as c where c.id=:id")
	// currentPage=page
	// contact per page=5
	//public Page<user> findcontactByUser(@Param("id") int id, Pageable pePageable);
	
	@Query("from user as d where d.id=:id")
	user balance(@Param("id") int id);
	
	 @Query("select s.balance from user s where s.id=:id")
	    int getBalance(@Param("id") int id);

	
}
