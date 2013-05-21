package com.ikkerens.whitelist.commands;

import static com.ikkerens.whitelist.commands.Command.ON;

import com.ikkerens.whitelist.Config;

import com.mbserver.api.CommandSender;
import com.mbserver.api.MBServerPlugin;

public class ToggleCommand implements SpecificCommand {

    public void execute( final MBServerPlugin plugin, final CommandSender sender, final Command cmd, final String[] args ) {
        final Config config = plugin.getConfig();

        config.setEnabled( cmd == ON ? true : false );
        plugin.saveConfig();

        sender.sendMessage( String.format( "Turned the whitelist %s", cmd.name() ) );
    }

}
