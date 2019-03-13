package com.secdn.secdnrapid.common.validator;

import com.secdn.secdnrapid.common.exception.SException;
import org.apache.commons.lang.StringUtils;


public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new SException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new SException(message);
        }
    }
}
