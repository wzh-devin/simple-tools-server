package com.devin.simpletools_server.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 2025/2/14 15:19
 * <p></p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public enum WsTypeEnum {

    SCAN_QR(1, "扫描二维码"),
    OTHER(-1, "其他"),
    ;

    private final Integer type;
    private final String desc;

    private static final Map<Integer, WsTypeEnum> cache;

    static {
        cache = Arrays.stream(WsTypeEnum.values()).collect(Collectors.toMap(WsTypeEnum::getType, Function.identity()));
    }

    public static WsTypeEnum of(Integer type) {
        return cache.get(type);
    }
}
