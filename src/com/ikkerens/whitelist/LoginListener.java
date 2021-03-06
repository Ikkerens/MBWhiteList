package com.ikkerens.whitelist;

import com.mbserver.api.events.EventHandler;
import com.mbserver.api.events.Listener;
import com.mbserver.api.events.PlayerLoginEvent;

public final class LoginListener implements Listener {
    private final Config config;

    public LoginListener( final Config config ) {
        this.config = config;
    }

    @EventHandler
    public void onLogin( final PlayerLoginEvent event ) {
        if ( !this.config.isWhitelisted( event.getPlayer().getName() ) )
            event.setCancelled( true, "You are not whitelisted on this server." );
    }
}
