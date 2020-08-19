package com.example.faizan.allconcepts.auth0;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.faizan.allconcepts.R;


public abstract class AuthAwareActivity extends AppCompatActivity {
    protected AuthenticationHandler authenticationHandler;
    protected Menu menu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.authenticationHandler = new AuthenticationHandler(this);

    }

    public void refreshMenu() {
        MenuItem firstOption = menu.findItem(R.id.first_action);

        // reconfiguring button
        if (!authenticationHandler.hasValidCredentials()) {
            firstOption.setTitle(R.string.login);
            firstOption.setOnMenuItemClickListener(new LoginListener(authenticationHandler));
        } else {
            firstOption.setTitle(R.string.logout);
            firstOption.setOnMenuItemClickListener(new LogoutListener(authenticationHandler));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        this.menu = menu;
        refreshMenu();

        return true;
    }
}