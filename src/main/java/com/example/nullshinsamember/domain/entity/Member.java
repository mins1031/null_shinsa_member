package com.example.nullshinsamember.domain.entity;

import com.example.nullshinsamember.common.domain.BaseEntity;
import com.example.nullshinsamember.domain.enumeration.MemberRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    private boolean isDelete;
    private MemberRole role;

    private Member(String email, String password, String name, boolean isDelete, MemberRole role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.isDelete = isDelete;
        this.role = role;
    }

    public static Member of(String email, String password, String name, boolean isDelete, MemberRole memberRole) {
        return new Member(email, password, name, isDelete, memberRole);
    }

    public boolean isMatchPassword(String password) {
        if (password.isBlank()) {
            return false;
        }

        return this.password.equals(password);
    }

    public String getRoleDetail() {
        return this.role.getRole();
    }
}
