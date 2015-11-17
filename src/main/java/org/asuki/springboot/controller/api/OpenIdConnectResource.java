package org.asuki.springboot.controller.api;

import com.nimbusds.oauth2.sdk.*;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.nimbusds.oauth2.sdk.id.State;
import com.nimbusds.openid.connect.sdk.AuthenticationRequest;
import com.nimbusds.openid.connect.sdk.AuthenticationSuccessResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("api")
public class OpenIdConnectResource {

    private static final Logger log = LoggerFactory.getLogger(OpenIdConnectResource.class);

    @RequestMapping(value = "login", method = GET)
    public void redirectCode(@RequestParam Map<String, String> queryParameters, HttpServletResponse resp)
            throws ParseException, SerializeException, IOException {

        log.info(queryParameters.toString());

        AuthenticationSuccessResponse authResponse = createAuthResponse(queryParameters);

        resp.setStatus(FOUND.value());
        resp.sendRedirect(authResponse.toURI().toString());
    }

    private AuthenticationSuccessResponse createAuthResponse(Map<String, String> queryParameters)
            throws ParseException {

        AuthenticationRequest request = AuthenticationRequest.parse(queryParameters);

        // Required to look up the client in database
        ClientID clientID = request.getClientID();

        // Required to be registered in database
        URI callbackUri = request.getRedirectionURI();

        ResponseType responseType = request.getResponseType();

        State state = request.getState();

        Scope scope = request.getScope();

        AuthorizationCode code = new AuthorizationCode();

        return new AuthenticationSuccessResponse(callbackUri, code, null, null, state);
    }

}
