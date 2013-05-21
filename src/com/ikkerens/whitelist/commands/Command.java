package com.ikkerens.whitelist.commands;

public enum Command {
    ON ( new ToggleCommand() ),
    OFF ( new ToggleCommand() ),
    ADD ( new ModifierCommand(), "[playername]" ),
    REMOVE ( new ModifierCommand(), "[playername]" );

    private SpecificCommand handler;
    private String[]        args;

    private Command( final SpecificCommand handler, final String... args ) {
        this.handler = handler;
        this.args = args;
    }

    public static Command match( final String cmd ) {
        try {
            return Command.valueOf( cmd.toUpperCase() );
        } catch ( final IllegalArgumentException e ) {
            return null;
        }
    }

    public String[] getArgs() {
        return this.args;
    }

    public SpecificCommand getHandler() {
        return this.handler;
    }
}
