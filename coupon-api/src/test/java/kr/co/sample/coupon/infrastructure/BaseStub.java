/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.infrastructure;

import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@AutoConfigureWireMock(port = 0, stubs = "classpath:/stubs/**/*.json")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public abstract class BaseStub {
    @Autowired protected MockMvc mockMvc;

    protected static final String X_MOCK_RESPONSE_HEADER = "X-WireMock-Who-Am-I";
}
