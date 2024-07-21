package com.example.nullshinsamember.utils;

public class MemberUtils {

    //TODO 이메일과 비밀번호로 세션값을 만드는게 이상하지만 일단 간단하게 구현하기 위해 임시로 적용합니다.
    public static String generateMemberSessionValue(final String email, final String password) {
        //TODO 만약 이런 공통 유틸메서드 파라미터가 null이면 예외처리를 하는게 나을까요? 아니면 null 케이스를 상정해서 결과를 만들어 내야할까요?
        String middleValue = "--";

        //TODO ide는 주석처리 안된 내용으로 하라고 추천하는데 StringBuilder를 활용한 문자열 병합이 성능상 더 좋지 않을까요?
        return email + middleValue + password;
//        return new StringBuilder(email).append(middleValue).append(password).toString();
    }
}
