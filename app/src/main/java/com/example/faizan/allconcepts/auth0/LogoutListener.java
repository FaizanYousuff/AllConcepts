package com.example.faizan.allconcepts.auth0;

import android.view.MenuItem;


public class LogoutListener implements MenuItem.OnMenuItemClickListener {
    private AuthenticationHandler authenticationHandler;

    public LogoutListener(AuthenticationHandler authenticationHandler) {
        this.authenticationHandler = authenticationHandler;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        authenticationHandler.logout();
        return true;
    }
}