package com.platform.common.core.constant.enums;

import com.platform.common.core.constant.NumberConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author szhua
 */
@Getter
@AllArgsConstructor
public enum StrNumberConstants implements NumberConstant<String> {
    /**
     * 0
     */
    ZERO("0"),
    /**
     * 1
     */
    ONE("1"),
    /**
     * 2
     */
    TWO("2"),
    /**
     * 3
     */
    THREE("3"),
    /**
     * 4
     */
    FOUR("4"),
    /**
     * 5
     */
    FIVE("5"),
    /**
     * 6
     */
    SIX("6");

    private final String number;
}
