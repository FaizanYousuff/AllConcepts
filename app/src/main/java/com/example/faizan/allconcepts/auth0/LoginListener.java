package com.example.faizan.allconcepts.auth0;

import android.view.MenuItem;



public class LoginListener implements MenuItem.OnMenuItemClickListener {
    private AuthenticationHandler authenticationHandler;

    public LoginListener(AuthenticationHandler authenticationHandler) {
        this.authenticationHandler = authenticationHandler;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        authenticationHandler.startAuthenticationProcess();
        return true;
    }
}