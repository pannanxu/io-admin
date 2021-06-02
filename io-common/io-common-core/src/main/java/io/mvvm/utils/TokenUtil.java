package io.mvvm.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.security.*;

/**
 * @program: io-admin
 * @description: Token Util
 * JKS 文件生成命令：
 *  keytool -genkey -alias jwt -keyalg RSA -keysize 1024 -keystore jwt.jks -validity 365 -storepass administrators -keypass 123456
 *
 * keytool -genkey --help    （命令中参数的解释）
 *  -alias <alias>                  要处理的条目的别名
 *  -keyalg <keyalg>                密钥算法名称
 *  -keysize <keysize>              密钥位大小
 *  -sigalg <sigalg>                签名算法名称
 *  -destalias <destalias>          目标别名
 *  -dname <dname>                  唯一判别名
 *  -startdate <startdate>          证书有效期开始日期/时间
 *  -ext <value>                    X.509 扩展
 *  -validity <valDays>             有效天数
 *  -keypass <arg>                  密钥口令
 *  -keystore <keystore>            密钥库名称
 *  -storepass <arg>                密钥库口令
 *  -storetype <storetype>          密钥库类型
 *  -providername <providername>    提供方名称
 *  -providerclass <providerclass>  提供方类名
 *  -providerarg <arg>              提供方参数
 *  -providerpath <pathlist>        提供方类路径
 *  -v                              详细输出
 *  -protected                      通过受保护的机制的口令
 * @author: Mr. Pan
 * @create: 2021-06-02 00:46
 **/
@Slf4j
@Component
public class TokenUtil {

    /**
     * Jks File Stream
      */
    private static InputStream JKS_FILE_STREAM = null;
    /**
     * 私钥
     */
    private static PrivateKey PRIVATE_KEY = null;
    /**
     * 公钥
     */
    private static PublicKey PUBLIC_KEY = null;
    /**
     * JKS 文件的资源路径
     */
    private static final String JKS_RESOURCE_PATH = "classpath:jwt.jks";
    /**
     * 密钥库密码
     */
    public static final String STORE_PASSWORD = "administrators";
    /**
     * 密钥密码
     */
    public static final String KEY_PASSWORD = "123456";
    /**
     * 命令生成整数文件时的别名
     */
    public static final String ALIAS = "jwt";
    /**
     * Token 过期时间
     * 从签发时间 -> 7天后过期
     */
    public static final int TOKEN_EXPIRE_DATE = 7;

    static {
        try {
            JKS_FILE_STREAM = new FileInputStream(ResourceUtils.getFile(JKS_RESOURCE_PATH));
        } catch (FileNotFoundException e) {
            log.error("读取JKS文件资源失败");
            e.printStackTrace();
        }

        try {
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(JKS_FILE_STREAM, STORE_PASSWORD.toCharArray());
            PRIVATE_KEY = (PrivateKey) keyStore.getKey(ALIAS, KEY_PASSWORD.toCharArray());
            PUBLIC_KEY = keyStore.getCertificate(ALIAS).getPublicKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成Token
     * @param uid   用户ID
     * @return      Token
     */
    public static String generateToken(String uid) {
        return Jwts.builder()
                .setClaims(null)
                .setSubject(uid)
                .setExpiration(DateUtil.getNextDay(TOKEN_EXPIRE_DATE))
                // 不使用公钥私钥
                // .signWith(SignatureAlgorithm.HS512, salt)
                .signWith(SignatureAlgorithm.RS256, PRIVATE_KEY)
                .compact();
    }

    /**
     * 解析Token
     * @param token Token
     * @return      用户ID
     */
    public static String parseToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    // 不使用公钥私钥
                    // .setSigningKey(salt)
                    .setSigningKey(PUBLIC_KEY)
                    .parseClaimsJws(token).getBody();
            return claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
