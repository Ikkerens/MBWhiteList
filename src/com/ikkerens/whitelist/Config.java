package com.ikkerens.whitelist;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public final class Config {
    private boolean                   installed;
    private boolean                   enabled;
    private final ArrayList< String > whitelist;

    public Config() {
        this.installed = false;
        this.enabled = true;
        this.whitelist = new ArrayList< String >();
    }

    void install( final WhiteListPlugin plugin ) {
        if ( !this.installed ) {
            final File oldWl = new File( "whitelist.txt" );
            if ( !oldWl.exists() )
                return;

            Scanner scanner = null;
            try {
                int count = 0;
                scanner = new Scanner( oldWl );

                while ( scanner.hasNextLine() ) {
                    this.whitelist.add( scanner.nextLine() );
                    count++;
                }

                plugin.getLogger().info( String.format( "Imported %s whitelisted accounts from old version.", count ) );
            } catch ( final FileNotFoundException e ) {
            } finally {
                this.installed = true;
                if ( scanner != null )
                    scanner.close();
            }
        }
    }

    public void setEnabled( final boolean enabled ) {
        this.enabled = enabled;
    }

    public void addToWhitelist( final String player ) {
        this.whitelist.add( player.toLowerCase() );
    }

    public void removeFromWhitelist( final String player ) {
        this.whitelist.remove( player.toLowerCase() );
    }

    public boolean isWhitelisted( final String player ) {
        if ( !this.enabled )
            return true;

        return this.whitelist.contains( player.toLowerCase() );
    }
}
