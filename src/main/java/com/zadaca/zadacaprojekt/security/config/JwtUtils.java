package com.zadaca.zadacaprojekt.security.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zadaca.zadacaprojekt.domain.User;
import com.zadaca.zadacaprojekt.service.UserManager;
import org.assertj.core.util.Strings;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public class JwtUtils {

    private static final Logger log = LoggerFactory.getLogger(JwtUtils.class);

    private final int jwtTokenExpiry = 15; //sessionId expires in 15 minutes


    private static String key = "dcDpssw1946$%rdp"; // must be 16 chars (bytes)


    public static String createJwt(User onlineUser) {
        String token = "";
        try {
            DateTime dateTime = new DateTime();
            dateTime = dateTime.plusMinutes(15);
            Algorithm algorithmHS = Algorithm.HMAC256(key);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("userId", onlineUser.getId())
//                    .withExpiresAt(dateTime.toDate())
                    .sign(algorithmHS);

        } catch (UnsupportedEncodingException e) {
            //UTF-8 encoding not supported
            log.error("Encoding not supported {}", e.getMessage());
        } catch (JWTCreationException e) {
            log.error("JWT creation exception {}", e.getMessage());

        }

        return token;
    }

    public static DecodedJWT jwtValid(String jwtToken) throws UnsupportedEncodingException,
            TokenExpiredException,
            JWTVerificationException {

        try {
            Algorithm algorithm = Algorithm.HMAC256("dcDpssw1946$%rdp");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance

            DecodedJWT jwt = verifier.verify(jwtToken);
            return jwt;

        } catch (TokenExpiredException e) {
            throw new TokenExpiredException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param request           user http request that should contain jwt token
     * @param onlineUserManager OnlineUserManager bean
     * @return OnlineUser instance if there is valid jwt token and there is user with given session id, null otherwise
     */
    public static User getOnlineUserFromRequest(HttpServletRequest request, UserManager onlineUserManager) {

        log.debug("Checking user for JWT auth");
        String authorization = request.getHeader("authorization");

        if (!Strings.isNullOrEmpty(authorization)) {
            String[] token = authorization.split(" ");

            try {
                DecodedJWT jwtValid = JwtUtils.jwtValid(token[1]);

                if (jwtValid != null) {
                    log.debug("Found valid JWT token: " + token);
                    Long userId = jwtValid.getClaim("userId").asLong();
                    return onlineUserManager.findById(userId);
                } else {
                    log.warn("No valid JWT token found in user request. Authorization header: " + authorization);
                }
            } catch (Exception e) {
                log.error("Error extracting user data from jwt", e);
            }
        } else {
            log.warn("No authorization header in user request!");
        }

        return null;
    }
}