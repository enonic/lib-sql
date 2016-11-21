package com.enonic.lib.sql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public final class SqlSource
{
    private final HikariConfig config;

    public SqlSource()
    {
        this.config = new HikariConfig();
    }

    public void setUrl( final String url )
    {
        this.config.setJdbcUrl( url );
    }

    public void setDriver( final String driver )
    {
        this.config.setDriverClassName( driver );
    }

    public void setUser( final String user )
    {
        this.config.setUsername( user );
    }

    public void setPassword( final String password )
    {
        this.config.setPassword( password );
    }

    public void setPoolName( final String name )
    {
        this.config.setPoolName( name );
    }

    public void setMaxPoolSize( final int size )
    {
        this.config.setMaximumPoolSize( size );
    }

    public void setMinPoolSize( final int size )
    {
        this.config.setMinimumIdle( size );
    }

    public HikariDataSource newDataSource()
    {
        return new HikariDataSource( this.config );
    }
}
