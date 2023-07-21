package com.java.jpa.repository;

import com.java.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    /*
    * A fetch join allows you to fetch related entities along with the main entity in a single query,
    * effectively solving the N+1 problem.
    * Use the JOIN FETCH keyword in your JPQL query to perform a fetch join.
    * */
    @Query("select u from User u JOIN FETCH u.addresses ")
    List<User> findAllUsersAndAddress();

}
