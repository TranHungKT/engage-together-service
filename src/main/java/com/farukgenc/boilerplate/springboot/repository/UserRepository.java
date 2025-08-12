package com.farukgenc.boilerplate.springboot.repository;

import com.farukgenc.boilerplate.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    List<User> findAllByIdIn(Collection<UUID> userIds);

    @Query(
            "select u from User u join OrganizationMember om on u.id = om.userId and om.organizationId = :organizationId " +
                    "where u.username like %:username%"
    )
    List<User> searchUser(UUID organizationId, String username);

}
