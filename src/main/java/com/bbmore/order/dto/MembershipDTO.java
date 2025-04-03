package com.bbmore.order.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class MembershipDTO {

    private int membershipCode;
    private String membershipName;

}
