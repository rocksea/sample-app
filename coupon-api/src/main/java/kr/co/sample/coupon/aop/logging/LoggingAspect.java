/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.aop.logging;

import static org.springframework.http.HttpHeaders.USER_AGENT;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class LoggingAspect {
    private final ObjectMapper objectMapper;

    @Around(
            "within(@org.springframework.web.bind.annotation.RestController *) && within(kr.co.sample.coupon..*)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            HttpServletRequest request = getServletRequest();
            Map<String, String[]> paramMap = request.getParameterMap();
            String requestParam = "";
            if (!paramMap.isEmpty()) {
                requestParam = String.format("[%s]", this.paramMapToString(paramMap));
            }

            DispatcherType dispatcherType = request.getDispatcherType();
            StringBuilder requestData = new StringBuilder();
            if (dispatcherType.equals(DispatcherType.REQUEST)) {
                for (Object obj : joinPoint.getArgs()) {
                    if (!(obj instanceof BindingResult)) {
                        requestData.append(objectMapper.writeValueAsString(obj));
                    }
                }
            }

            log.info(
                    "IP [{}], Agent[{}], [{}]:{}, Param: {}, Body: {}",
                    this.getIp(request),
                    request.getHeader(USER_AGENT),
                    request.getMethod(),
                    request.getRequestURI(),
                    requestParam,
                    requestData);
        } catch (Exception e) {
            log.error("logging Error", e);
        }

        return joinPoint.proceed();
    }

    private HttpServletRequest getServletRequest() {
        HttpServletRequest request =
                ((ServletRequestAttributes)
                                Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                        .getRequest();
        return request;
    }

    private String getResponse() {
        return "";
    }

    private String paramMapToString(Map<String, String[]> paramMap) {
        return paramMap.entrySet().stream()
                .map(
                        entry ->
                                String.format("%s -> (%s)", entry.getKey(), String.join(", ", entry.getValue())))
                .collect(Collectors.joining(", "));
    }

    private String getIp(HttpServletRequest request) {
        return Objects.nonNull(request.getHeader("X-Forwarded-For"))
                ? request.getHeader("X-Forwarded-For")
                : request.getRemoteAddr();
    }
}
