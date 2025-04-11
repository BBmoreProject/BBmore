package com.bbmore.admin.aorder.repository;

import com.bbmore.admin.aorder.dto.aReturnSearchResultDTO;
import com.bbmore.order.entity.UserReturn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface aReturnRepository extends JpaRepository<UserReturn, Long> {

    @Query(value = """
           SELECT DISTINCT new com.bbmore.admin.aorder.dto.aReturnSearchResultDTO(
                   ur.returnCode,
                   ur.returnRequestDate,
                   ur.returnStatus,
                   ur.refundAmount,
                   ur.returnReason,
                   m.userName
           )
           FROM OrderDetail od
           JOIN od.userReturn ur
           JOIN od.order o
           JOIN o.member m
           WHERE (:returnCode IS NULL OR CAST(ur.returnCode AS string) = :returnCode)
             AND (:returnStatus IS NULL OR ur.returnStatus = :returnStatus)
             AND (:memberName IS NULL OR m.userName LIKE %:memberName%)
             AND (:startDate IS NULL OR ur.returnRequestDate >= :startDate)
             AND (:endDate IS NULL OR ur.returnRequestDate <= :endDate)
           """,
            countQuery = """
           SELECT COUNT(DISTINCT ur.returnCode)
           FROM OrderDetail od
           JOIN od.userReturn ur
           JOIN od.order o
           JOIN o.member m
           WHERE (:returnCode IS NULL OR CAST(ur.returnCode AS string) = :returnCode)
             AND (:returnStatus IS NULL OR ur.returnStatus = :returnStatus)
             AND (:memberName IS NULL OR m.userName LIKE %:memberName%)
             AND (:startDate IS NULL OR ur.returnRequestDate >= :startDate)
             AND (:endDate IS NULL OR ur.returnRequestDate <= :endDate)
           """)
    Page<aReturnSearchResultDTO> findReturnDetailsPage(
            @Param("returnCode") String returnCode,
            @Param("returnStatus") Boolean returnStatus,
            @Param("memberName") String memberName,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable);

    @Modifying
    @Query("UPDATE UserReturn ur SET ur.returnStatus = :status WHERE ur.returnCode = :code")
    int updateReturnStatus(@Param("code") Integer code, @Param("status") Boolean status);
}
