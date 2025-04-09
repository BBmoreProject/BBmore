package com.bbmore.admin.aorder.repository;

import com.bbmore.admin.aorder.dto.aExchangeSearchResultDTO;
import com.bbmore.order.entity.UserExchange;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface aExchangeRepository extends JpaRepository<UserExchange, Long> {

    @Query(value = """
           SELECT DISTINCT new com.bbmore.admin.aorder.dto.aExchangeSearchResultDTO(
                   ue.exchangeCode,
                   ue.exchangeRequestDate,
                   ue.exchangeStatus,
                   ue.exchangeReason,
                   m.userName
           )
           FROM OrderDetail od
           JOIN od.userExchange ue
           JOIN od.order o
           JOIN o.member m
           WHERE (:exchangeCode IS NULL OR CAST(ue.exchangeCode AS string) = :exchangeCode)
             AND (:exchangeStatus IS NULL OR ue.exchangeStatus = :exchangeStatus)
             AND (:memberName IS NULL OR m.userName LIKE %:memberName%)
             AND (:startDate IS NULL OR ue.exchangeRequestDate >= :startDate)
             AND (:endDate IS NULL OR ue.exchangeRequestDate <= :endDate)
           """,
            countQuery = """
           SELECT COUNT(DISTINCT ue.exchangeCode)
           FROM OrderDetail od
           JOIN od.userExchange ue
           JOIN od.order o
           JOIN o.member m
           WHERE (:exchangeCode IS NULL OR CAST(ue.exchangeCode AS string) = :exchangeCode)
             AND (:exchangeStatus IS NULL OR ue.exchangeStatus = :exchangeStatus)
             AND (:memberName IS NULL OR m.userName LIKE %:memberName%)
             AND (:startDate IS NULL OR ue.exchangeRequestDate >= :startDate)
             AND (:endDate IS NULL OR ue.exchangeRequestDate <= :endDate)
           """)
    Page<aExchangeSearchResultDTO> findExchangeDetailsPage(
            @Param("exchangeCode") String exchangeCode,
            @Param("exchangeStatus") Boolean exchangeStatus,
            @Param("memberName") String memberName,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable);

    @Modifying
    @Query("UPDATE UserExchange ue SET ue.exchangeStatus = :status WHERE ue.exchangeCode = :code")
    int updateExchangeStatus(@Param("code") Integer code, @Param("status") Boolean status);
}
