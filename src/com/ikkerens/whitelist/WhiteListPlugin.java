package com.ikkerens.whitelist;

import com.mbserver.api.MBServerPlugin;
import com.mbserver.api.Manifest;

@Manifest( name = "MBWhiteList", authors = "Ikkerens", config = Config.class, isPubliclyForbidden = true )
public final class WhiteListPlugin extends MBServerPlugin {

    @Override
    public void onEnable() {
        final Config config = this.getConfig();
        config.install( this );

        this.getPluginManager().registerEventHandler( new LoginListener( config ) );
        this.getPluginManager().registerCommand( "whitelist", new String[] { "wl" }, new GenericCommand( this ) );
    }

}
