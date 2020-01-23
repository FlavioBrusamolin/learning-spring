package com.leucotron.learningspring.config.security.filter;

import com.leucotron.learningspring.config.security.JSecurityConstants;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

/**
 *
 * @author flavio
 */
public class JAuthorizationFilter extends BasicAuthenticationFilter {

    public JAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws IOException, ServletException {

        var authentication = getAuthentication(request);

        if (authentication == null) {
            filterChain.doFilter(request, response);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        var token = request.getHeader(JSecurityConstants.TOKEN_HEADER);
        
        if (!StringUtils.isEmpty(token) && token.startsWith(JSecurityConstants.TOKEN_PREFIX)) {
            try {
                var decodedToken = Jwts
                        .parser()
                        .setSigningKey(JSecurityConstants.JWT_SECRET)
                        .parseClaimsJws(token.replace(JSecurityConstants.TOKEN_PREFIX, ""))
                        .getBody();

                var username = decodedToken.getSubject();
//                var profile = decodedToken.get("profile");
//
//                var authorities = new ArrayList<GrantedAuthority>();
//                authorities.add((GrantedAuthority) profile);

                if (!StringUtils.isEmpty(username)) {
                    return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
                }
            } catch (ExpiredJwtException
                    | UnsupportedJwtException
                    | MalformedJwtException
                    | SignatureException
                    | IllegalArgumentException ex) {

                System.err.println(ex.getMessage());
            }
        }

        return null;
    }

}
