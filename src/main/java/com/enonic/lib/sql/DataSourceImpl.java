package com.enonic.lib.sql;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

final class DataSourceImpl
    implements DataSource
{
    private PrintWriter writer;

    private String url;

    private String driver;

    private String user;

    private String password;

    public void setUrl( final String url )
    {
        this.url = url;
    }

    public void setDriver( final String driver )
    {
        this.driver = driver;
    }

    public void setUser( final String user )
    {
        this.user = user;
    }

    public void setPassword( final String password )
    {
        this.password = password;
    }

    public void init()
        throws Exception
    {
        Class.forName( this.driver );
    }

    @Override
    public Connection getConnection()
        throws SQLException
    {
        return getConnection( this.user, this.password );
    }

    @Override
    public Connection getConnection( final String user, final String password )
        throws SQLException
    {
        return DriverManager.getConnection( this.url, user, password );
    }

    @Override
    public PrintWriter getLogWriter()
        throws SQLException
    {
        return this.writer;
    }

    @Override
    public void setLogWriter( final PrintWriter out )
        throws SQLException
    {
        this.writer = out;
    }

    @Override
    public void setLoginTimeout( final int seconds )
        throws SQLException
    {
        // Do nothing
    }

    @Override
    public int getLoginTimeout()
        throws SQLException
    {
        return 0;
    }

    @Override
    public Logger getParentLogger()
        throws SQLFeatureNotSupportedException
    {
        return null;
    }

    @Override
    public <T> T unwrap( final Class<T> iface )
        throws SQLException
    {
        return null;
    }

    @Override
    public boolean isWrapperFor( final Class<?> iface )
        throws SQLException
    {
        return false;
    }
}
