package com.example.nullshinsamember.domain.enumeration;

import lombok.Getter;

@Getter
public enum MemberRole {
    NORMAL_CONSUMER("ROLE_CONSUMER", "일반 구매자"),
    SELLER("ROLE_SELLER", "판매자(입점 브랜드)"),
    ADMIN("ROLE_ADMIN", "관리자"),

    ;

    private final String role;
    private final String desc;

    MemberRole(String role, String desc) {
        this.role = role;
        this.desc = desc;
    }
}
