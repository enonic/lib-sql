package com.enonic.lib.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.skife.jdbi.v2.tweak.ConnectionFactory;

final class ConnectionFactoryImpl
    implements ConnectionFactory
{
    protected String url;

    protected String driver;

    protected String user;

    protected String password;

    public void init()
        throws Exception
    {
        Class.forName( this.driver );
    }

    @Override
    public Connection openConnection()
        throws SQLException
    {
        return DriverManager.getConnection( this.url, this.user, this.password );
    }
}
