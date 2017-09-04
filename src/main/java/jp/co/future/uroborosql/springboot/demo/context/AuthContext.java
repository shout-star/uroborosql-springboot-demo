package jp.co.future.uroborosql.springboot.demo.context;

import io.jsonwebtoken.Claims;

/**
 * AuthContext
 *
 * @author Kenichi Hoshi
 */
public class AuthContext {

    private static ThreadLocal<Claims> claims = new ThreadLocal<>();

    /**
     * Claimsを追加する
     *
     * @param claims Claims
     */
    public static void addClaims(Claims claims) {
        AuthContext.claims.set(claims);
    }

    /**
     * Claimsを取得する
     *
     * @return カレントのClaims
     */
    public static Claims getClaims() {
        return AuthContext.claims.get();
    }

    /**
     * Claimsを削除する
     */
    public static void removeClaims() {
        AuthContext.claims.remove();
    }
}
