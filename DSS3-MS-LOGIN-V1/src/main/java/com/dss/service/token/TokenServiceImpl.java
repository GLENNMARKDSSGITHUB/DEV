package com.dss.service.token;

import com.dss.entity.action.Action;
import com.dss.entity.permission.Permission;
import com.dss.entity.resources.Resources;
import com.dss.entity.roles.Roles;
import com.dss.entity.token.UsersToken;
import com.dss.entity.user.Users;
import com.dss.repository.action.ActionRepository;
import com.dss.repository.permission.PermissionRepository;
import com.dss.repository.resources.ResourcesRepository;
import com.dss.repository.token.UsersTokenRepository;
import com.dss.repository.user.UsersRepository;
import com.dss.util.jwt.JwtProperties;
import com.dss.util.utils.CommonStringUtility;
import com.dss.util.utils.DssCommonMessageDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.dss.util.enums.UserStatus.ACTIVE;
import static com.dss.util.enums.UserStatus.INACTIVE;
import static com.dss.util.jwt.JwtProperties.*;

@Service
@Slf4j
public class TokenServiceImpl implements TokenService{

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersTokenRepository usersTokenRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private ResourcesRepository resourcesRepository;

    @Autowired
    private ActionRepository actionRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public DssCommonMessageDetails generateToken(String email, String password){
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        Map<String, String> tokenMap = new HashMap<>();
        Users user = usersRepository.findUserByEmailAddress(email);

        if(!user.getStatus().equalsIgnoreCase(ACTIVE.toString())){
            commonMsgDtl.setSuccess(false);
            commonMsgDtl.setContent(CommonStringUtility.ERR_CODE_INACTIVE_SUSPENDED);
        }

        if(!Objects.isNull(user)){
            if(encoder.matches(password, user.getPassword())){
                Claims claims = Jwts.claims().setSubject(email);
                Date exp = new Date(System.currentTimeMillis() + EXPIRATION_TIME);

                List<String> rolesList = new ArrayList<>();
                List<String> permissionsList = new ArrayList<>();
                List<String> resourcesList = new ArrayList<>();
                List<String> actionsList = new ArrayList<>();
                for(Roles role : user.getUserRoles()){
                    rolesList.add(role.getUserRole());
                    for(Permission permission : role.getPermission()){
                        permissionsList.add(permission.getPermissionName());
                        for(Resources resource : permission.getResources()){
                            resourcesList.add(resource.getResourceName());
                            for(Action action : resource.getAction()){
                                actionsList.add(action.getActionName());
                            }
                        }
                    }
                }

                String access_token = Jwts.builder()
                        .setHeaderParam("typ",HEADER_STRING)
                        .setClaims(claims)
                        .claim("userId", user.getDssUserId())
                        .claim("roles", rolesList)
                        .claim("permissions", permissionsList)
                        .claim("resources", resourcesList)
                        .claim("actions", actionsList)
                        .setIssuer(REQUEST_URL)
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(exp)
                        .signWith(SignatureAlgorithm.HS512, JWT_SECRET.getBytes())
                        .compact();

                String refresh_token = Jwts.builder()
                        .setHeaderParam("typ", HEADER_STRING)
                        .setClaims(claims)
                        .claim("userId", user.getDssUserId())
                        .claim("roles", rolesList)
                        .claim("permissions", permissionsList)
                        .claim("resources", resourcesList)
                        .claim("actions", actionsList)
                        .setIssuer(REQUEST_URL)
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(exp)
                        .signWith(SignatureAlgorithm.HS512, JWT_SECRET.getBytes())
                        .compact();

                UsersToken token = new UsersToken();
                token.setAccessToken(access_token);
                token.setRefreshToken(access_token);
                token.setUser(user);
                usersTokenRepository.save(token);

                log.debug("TokenServiceImpl | generateToken | access_token : {}", access_token);
                log.debug("TokenServiceImpl | generateToken | refresh_token : {}", refresh_token);
                log.debug("TokenServiceImpl | generateToken | expires at : {}", new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME));

                tokenMap.put("access_token", access_token);
                tokenMap.put("refresh_token", refresh_token);
                commonMsgDtl.setSuccess(true);
                commonMsgDtl.setContent("Logged in successfully");
                commonMsgDtl.setObjMap(tokenMap);
            }else{
                commonMsgDtl.setSuccess(false);
                commonMsgDtl.setContent(CommonStringUtility.ERR_CODE_LOGIN_INCORRECT_PASSWORD);
            }
        }else{
            commonMsgDtl.setSuccess(false);
            commonMsgDtl.setContent(CommonStringUtility.ERR_CODE_LOGIN_EMAIL_NOT_CONNECTED);
        }
        return commonMsgDtl;
    }
}
