package com.example.nullshinsamember.infrastructure.repository;

import com.example.nullshinsamember.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //TODO 방법 1 : Optional로 감싸서 처리  방법 2 : aggregation layer 구현 (지금같이 다른 쿼리메서드와 조합할 일이 없는 케이스에서도 굳이 aggregation 레이어가 필요할까..?)  방법 3 : 아래처럼 리턴후 서비스에서 예외처리
    Optional<Member> findByEmail(String email);
}
