package com.ikkerens.whitelist;

import com.ikkerens.whitelist.commands.Command;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.MBServerPlugin;

public final class GenericCommand implements CommandExecutor {
    private static final String  NO_PERMISSION  = "You do not have permission to use /%s";
    private static final String  USAGE_SPECIFIC = "Usage: /%s %s %s";
    private static final String  USAGE          = String.format( USAGE_SPECIFIC, "%s", "<on|off|add|remove>", "[playername]" );

    private final MBServerPlugin plugin;

    public GenericCommand( final MBServerPlugin plugin ) {
        this.plugin = plugin;
    }

    public final void execute( final String command, final CommandSender sender, final String[] args, final String label ) {
        try {
            if ( !sender.hasPermission( "mbserver.whitelist" ) )
                throw new IllegalArgumentException( String.format( NO_PERMISSION, label ) );

            if ( args.length == 0 )
                throw new IllegalArgumentException( String.format( USAGE, label ) );

            final Command cmd = Command.match( args[ 0 ] );
            if ( cmd == null )
                throw new IllegalArgumentException( String.format( USAGE, label ) );

            final String[] eArgs = cmd.getArgs();

            if ( eArgs.length != ( args.length - 1 ) )
                throw new IllegalArgumentException( String.format( USAGE_SPECIFIC, label, args[ 0 ], this.join( eArgs, " " ) ) );

            final String[] pArgs = new String[ eArgs.length ];
            for ( int i = 0; i < eArgs.length; i++ )
                pArgs[ i ] = args[ i + 1 ];

            cmd.getHandler().execute( this.plugin, sender, cmd, pArgs );
        } catch ( final IllegalArgumentException e ) {
            sender.sendMessage( e.getMessage() );
        }
    }

    private final String join( final String[] strings, final String glue ) {
        if ( strings.length == 0 )
            return "";

        String returnValue = strings[ 0 ];
        for ( int i = 1; i < strings.length; i++ )
            returnValue += glue + strings[ i ];

        return returnValue;
    }
}
