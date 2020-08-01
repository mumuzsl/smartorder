package com.cqjtu.smartorder.util;

import com.cqjtu.smartorder.api.UserService;
import com.cqjtu.smartorder.api.model.User;
import com.cqjtu.smartorder.dao.support.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.cqjtu.smartorder.util.StringUtils.hasTextAndNoWhitespaceAndLessLength;

/**
 * @author mumu
 * @date 2020/7/25
 */
public class EasyUtils {

    private static final Random RANDOM = new Random();
    private static int countNum = 0;

    private EasyUtils() {}

    public static int count() {
        int temp = countNum;
        countNum = ++countNum % 900;
        return 100 + temp;
    }

    public static String getLongId(int count) {
        return String.valueOf(Instant.now().toEpochMilli())
                + (10 + RANDOM.nextInt(89))
                + count;
    }

    public static Long getLongId() {
        return Long.valueOf(getLongId(count()));
    }

    public static Optional<String> authenticationUsername() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Principal::getName);
    }

    public static boolean verifyUserInfo(String username, String password) {
        return hasTextAndNoWhitespaceAndLessLength(username, 30)
                && hasTextAndNoWhitespaceAndLessLength(password, 18);
    }

    public static Map<String, Object> toMap(PageRequest pageRequest) {
        Map<String, Object> map = new HashMap<>();
        map.put("offset", pageRequest.getOffset());
        map.put("rows", pageRequest.getRows());

        return map;
    }

    public static boolean verifyUserInfo(User user) {
        return Wapper.of(user).map(User::getUsername, User::getPassword, EasyUtils::verifyUserInfo);
    }

    public static class Wapper<T> {
        private T value;

        private Wapper(T value) {
            this.value = value;
        }

        public <E, U> U map(Function<? super T, ? extends E> fun1,
                            Function<? super T, ? extends E> fun2,
                            BiFunction<? super E, ? super E, ? extends U> biFunction) {
            return biFunction.apply(fun1.apply(value), fun2.apply(value));
        }

        public static <T> Wapper<T> of(T t) {
            return new Wapper<>(t);
        }
    }
}
