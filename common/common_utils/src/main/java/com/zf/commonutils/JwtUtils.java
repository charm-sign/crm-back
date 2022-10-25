package com.zf.commonutils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @ClassName: JwtUtils
 * @Description: TODO
 * @Author: ZF
 * @date: 2022/10/10 21:30
 */
public class JwtUtils {
//常量
        public static final long EXPIRE = 1000 * 60 * 60 * 24; //过期时间自定义）
        public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO"; //秘钥（自定义）
        public static String getJwtToken(String id, String nickname){
            String JwtToken = Jwts.builder()
                    //设置JWT头信息（固定）
                    .setHeaderParam("typ", "JWT")
                    .setHeaderParam("alg", "HS256")

                    .setSubject("charm_sign") //自定义
                    .setIssuedAt(new Date()) //设置过期时间
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                    //设置token主题部分 存储用户信息
                    .claim("id", id)  //自定义
                    .claim("nickname", nickname)//自定义
                    //生成
                    .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                    .compact();
            return JwtToken;
        }
        /**
         * 判断token是否存在与有效
         * @param jwtToken
         * @return
         */
        public static boolean checkToken(String jwtToken) {
            if(StringUtils.isEmpty(jwtToken)) return false;
            try {
                Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        /**
         * 判断token是否存在与有效
         * @param request
         * @return
         */
        public static boolean checkToken(HttpServletRequest request) {
            try {
                String jwtToken = request.getHeader("token");
                if(StringUtils.isEmpty(jwtToken)) return false;
                Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        /**
         * 根据token字符串获取信息会员id
         * @param request
         * @return
         */
        public static String getMemberIdByJwtToken(HttpServletRequest request) {
            String jwtToken = request.getHeader("token");
            if(StringUtils.isEmpty(jwtToken)) return "";
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
            Claims claims = claimsJws.getBody();
            return (String)claims.get("id");
        }
    }

