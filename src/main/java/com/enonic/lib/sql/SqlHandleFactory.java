package com.enonic.lib.sql;

import org.skife.jdbi.v2.DBI;

public final class SqlHandleFactory
{
    private final ConnectionFactoryImpl connectionFactory;

    public SqlHandleFactory()
    {
        this.connectionFactory = new ConnectionFactoryImpl();
    }

    public void setUrl( final String url )
    {
        this.connectionFactory.url = url;
    }

    public void setDriver( final String driver )
    {
        this.connectionFactory.driver = driver;
    }

    public void setUser( final String user )
    {
        this.connectionFactory.user = user;
    }

    public void setPassword( final String password )
    {
        this.connectionFactory.password = password;
    }

    public SqlHandle create()
        throws Exception
    {
        this.connectionFactory.init();
        final DBI dbi = new DBI( this.connectionFactory );
        return new SqlHandle( dbi );
    }
}
