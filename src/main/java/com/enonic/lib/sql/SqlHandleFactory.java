package com.enonic.lib.sql;

import org.skife.jdbi.v2.DBI;

public final class SqlHandleFactory
{
    private final DataSourceImpl dataSource;

    public SqlHandleFactory()
    {
        this.dataSource = new DataSourceImpl();
    }

    public void setUrl( final String url )
    {
        this.dataSource.setUrl( url );
    }

    public void setDriver( final String driver )
    {
        this.dataSource.setDriver( driver );
    }

    public void setUser( final String user )
    {
        this.dataSource.setUser( user );
    }

    public void setPassword( final String password )
    {
        this.dataSource.setPassword( password );
    }

    public SqlHandle create()
        throws Exception
    {
        this.dataSource.init();
        final DBI dbi = new DBI( this.dataSource );
        return new SqlHandle( dbi );
    }
}
