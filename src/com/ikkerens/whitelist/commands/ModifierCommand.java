package com.ikkerens.whitelist.commands;

import com.ikkerens.whitelist.Config;

import com.mbserver.api.CommandSender;
import com.mbserver.api.MBServerPlugin;

public class ModifierCommand implements SpecificCommand {

    public void execute( final MBServerPlugin plugin, final CommandSender sender, final Command cmd, final String[] args ) {
        final Config config = plugin.getConfig();

        switch( cmd ) {
            case ADD:
                config.addToWhitelist( args[ 0 ] );
                break;
            case REMOVE:
                config.removeFromWhitelist( args[ 0 ] );
                break;
            default:
                throw new RuntimeException();
        }

        plugin.saveConfig();
        sender.sendMessage( String.format( "Player %s was %sd from the whitelist.", args[ 0 ], cmd.name().toLowerCase() ) );
    }

}
