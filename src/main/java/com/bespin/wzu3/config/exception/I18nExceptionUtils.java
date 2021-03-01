package com.bespin.wzu3.config.exception;

import com.bespin.wzu3.constants.APIConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Objects;

/**
 * @author voidm
 * @date 2020/11/30
 */

@Component
@Slf4j
public class I18nExceptionUtils {

    @Autowired
    private MessageSource messageSource;

    /**
     * 获取国际化异常信息
     */
    public String getLocaleMessage (HttpServletRequest request, String code, String defaultMsg, Object[] params) {
        String language = request.getHeader("lang");
        Locale locale = Objects.nonNull(language) ? new Locale(language) : Locale.getDefault();
        try {
            return messageSource.getMessage(code == null ? APIConstant.Msg.DEFAULT_SERVER_EXCEPTION : code, params, locale);
        } catch (Exception e) {
            log.warn("本地化异常消息发生异常: {}, {}", code, params);
            return defaultMsg;
        }
    }
}