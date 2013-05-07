package com.ikkerens.whitelist.commands;

import com.mbserver.api.CommandSender;
import com.mbserver.api.MBServerPlugin;

public interface SpecificCommand {
    public void execute( MBServerPlugin plugin, CommandSender sender, Command cmd, String[] args );
}
