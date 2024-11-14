package com.revature.woodgateP1.daos;

import com.revature.woodgateP1.models.Reimb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReimbDAO extends JpaRepository<Reimb, Integer> {
    public Reimb getReferenceById(int reimbId);
    public List<Reimb> findByUserUserId(int userId);
    public List<Reimb> findByStatus(String Status);
    public List<Reimb> findByUserUserIdAndStatus(int userId, String status);
    long countByStatus(String status);
    @Query(value = "SELECT SUM(amount) FROM reimbs WHERE user_id = :userId AND status = 'APPROVED'", nativeQuery = true)
    long calcSumOfApprovedById(@Param("userId") int userId);
}


