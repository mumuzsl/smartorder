package com.cqjtu.smartorder.util;

import com.cqjtu.smartorder.api.param.IMParam;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author mumu
 * @date 2020/7/29
 */
public class IMUtils {

    private static List<IMParam> IMParams = new ArrayList<>(30);

    private static Long time = Instant.now().toEpochMilli();

    public static boolean add(IMParam imParam) {
        return IMParams.add(imParam);
    }

    public static IMParam remove() {
        if (IMParams.isEmpty()) {
            return null;
        }
        return IMParams.remove(0);
    }

    public static IMParam get() {
        if (IMParams.isEmpty()) {
            return null;
        }
        return IMParams.get(0);
    }

    public static List gets() {
        return IMParams;
    }

    public static boolean timeLimit() {
        return Instant.now().toEpochMilli() - time > 0x2bf20;
    }
}
