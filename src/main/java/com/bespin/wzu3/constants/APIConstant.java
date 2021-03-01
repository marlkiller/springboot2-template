package com.bespin.wzu3.constants;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class APIConstant {
    public static class Msg {
        public static final String DEFAULT_SERVER_EXCEPTION = "DEFAULT_SERVER_EXCEPTION {0}";
    }
}