package edu.kit.pse.fridget.server.services;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Optional;

import edu.kit.pse.fridget.server.exceptions.UnauthorizedException;

public class AuthenticationService {
    private final HttpTransport transport = new NetHttpTransport();

    private final JsonFactory jsonFactory = new JacksonFactory();

    public AuthenticationService() {
    }

    public GoogleIdToken.Payload verifyIdTokenAndGetPayload(String idTokenString) {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory).build();

        try {
            return Optional.ofNullable(verifier.verify(idTokenString)).orElseThrow(UnauthorizedException::buildForBrokenToken).getPayload();
        } catch (GeneralSecurityException e) {
            throw UnauthorizedException.buildForBrokenToken();
        } catch (IOException e) {
            throw UnauthorizedException.buildForConnectionProblem();
        }
    }
}
