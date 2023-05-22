package com.eventManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eventManagement.model.UserRewards;

@Repository
public interface UserRewardsRepository extends JpaRepository<UserRewards, Long> {

	@Query("Select user from UserRewards user where user.adminId = :adminId")
	List<UserRewards> findByAdminId(@Param("adminId") Long adminId);

	@Query("Select user from UserRewards user where user.adminId = :adminId AND user.rewardPoints <= :rewardRange")
	List<UserRewards> findByAdminIdAndReward(@Param("adminId") Long adminId, @Param("rewardRange") Long rewardRange);

	@Query("Select user from UserRewards user where user.adminId = :adminId AND LOWER(user.username) LIKE :username")
	List<UserRewards> findByAdminIdAndUserName(@Param("adminId") Long adminId, @Param("username")String username);

}
