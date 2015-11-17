package org.asuki.springboot.controller.api;

import com.nimbusds.oauth2.sdk.*;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.nimbusds.oauth2.sdk.id.State;
import com.nimbusds.oauth2.sdk.token.AccessToken;
import com.nimbusds.oauth2.sdk.token.BearerAccessToken;
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

import static com.nimbusds.oauth2.sdk.ResponseType.Value.CODE;
import static com.nimbusds.oauth2.sdk.ResponseType.Value.TOKEN;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("api")
public class Oauth2Resource {

    private static final Logger log = LoggerFactory.getLogger(Oauth2Resource.class);

    @RequestMapping(value = "authz", method = GET)
    public void redirect(@RequestParam Map<String, String> queryParameters, HttpServletResponse resp)
            throws ParseException, SerializeException, IOException {

        log.info(queryParameters.toString());

        AuthorizationResponse authResponse = createAuthResponse(queryParameters);

        resp.sendRedirect(authResponse.toURI().toString());
    }

    private AuthorizationResponse createAuthResponse(Map<String, String> queryParameters)
            throws ParseException {

        boolean authorizationDenied = false;

        AuthorizationRequest request = AuthorizationRequest.parse(queryParameters);

        // Required to look up the client in database
        ClientID clientID = request.getClientID();

        // Required to be registered in database
        URI callbackUri = request.getRedirectionURI();

        ResponseType responseType = request.getResponseType();

        State state = request.getState();

        Scope scope = request.getScope();

        if (authorizationDenied) {
            return new AuthorizationErrorResponse(
                    callbackUri,
                    OAuth2Error.ACCESS_DENIED,
                    responseType,
                    state);
        }

        if (responseType.equals(new ResponseType(CODE))) {

            AuthorizationCode code = new AuthorizationCode();
            return new AuthenticationSuccessResponse(callbackUri, code, null, null, state);
        } else if (responseType.equals(new ResponseType(TOKEN))) {

            AccessToken token = new BearerAccessToken();
            return new AuthenticationSuccessResponse(callbackUri, null, null, token, state);
        }

        throw new RuntimeException("Unsupported response type: " + responseType);
    }
}
