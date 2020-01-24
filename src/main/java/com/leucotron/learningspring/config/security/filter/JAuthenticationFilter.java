package com.leucotron.learningspring.config.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leucotron.learningspring.config.security.JSecurityConstants;
import com.leucotron.learningspring.response.JSuccessResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author flavio
 */
public class JAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

        setFilterProcessesUrl(JSecurityConstants.AUTH_URL);
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {

        var username = request.getParameter("username");
        var password = request.getParameter("password");

        var authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain,
            Authentication authentication) throws IOException, ServletException {

        var user = (User) authentication.getPrincipal();

        var token = Jwts
                .builder()
                .setSubject(user.getUsername())
//                .claim("profile", user.getProfile())
                .setExpiration(new Date(System.currentTimeMillis() + JSecurityConstants.JWT_EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, JSecurityConstants.JWT_SECRET)
                .compact();

        var responseBody = new JSuccessResponse("Successful login");
        responseBody.setData("token", token);

        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getWriter(), responseBody);
    }

}
