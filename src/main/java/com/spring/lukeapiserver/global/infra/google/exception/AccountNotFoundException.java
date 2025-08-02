package com.spring.lukeapiserver.global.infra.google.exception;


import com.spring.lukeapiserver.global.exception.BusinessException;
import com.spring.lukeapiserver.global.infra.google.exception.error.GoogleError;

// 구글 계정을 찾을 수 없을 경우 에러
public class AccountNotFoundException extends BusinessException {

    // 예외 인스턴스를 전역적으로 사용할 수 있도록 static으로 선언
    public static final AccountNotFoundException EXCEPTION = new AccountNotFoundException();

    // private 생성자를 통해 외부에서 인스턴스를 생성할 수 없도록 함
    private AccountNotFoundException(){
        // 부모 클래스인 BusinessException의 생성자를 호출하며, GoogleError.ACCOUNT_NOT_FOUND를 전달
        super(GoogleError.ACCOUNT_NOT_FOUND);
    }

}
