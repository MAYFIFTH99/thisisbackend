package kr.co.hanbit.product.management.application;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

// 유효성 검사는 Service Layer에서 진행하지만,
// 유효성 검사에 필요한 Domain 지식은 Product(Domain) 내에 머물도록 한다.

    @Service
    @Validated
    public class ValidationService {
        public <T> void checkValid(@Valid T vaidationTarget) {
            // do nothing
        }
    }
