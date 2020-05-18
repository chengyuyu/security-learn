package com.example.securitylearn.filter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

class VerificationCodeException extends AuthenticationException {
    public VerificationCodeException(){
        super("图形验证码校验失败");
    }
}

public class VerificationCodeFilter extends OncePerRequestFilter {

    private AuthenticationFailureHandler authenticationFailureHandler =
            new AuthenticationFailureHandler() {
                @Override
                public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, org.springframework.security.core.AuthenticationException e) throws IOException, ServletException {

                }
            };

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if(!"/auth/form".equals(httpServletRequest.getRequestURI())){

        }else {
            try {
                verificationCode(httpServletRequest);
            }catch (VerificationCodeException e){
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
            }
        }
    }

    public void verificationCode(HttpServletRequest httpServletRequest) throws VerificationCodeException{

    }
}
