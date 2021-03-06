package com.enonic.lib.sql;

import java.util.List;
import java.util.Map;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.Query;

import com.zaxxer.hikari.HikariDataSource;

public final class SqlHandle
{
    private final DBI dbi;

    private final HikariDataSource dataSource;

    public SqlHandle( final HikariDataSource dataSource )
    {
        this.dataSource = dataSource;
        this.dbi = new DBI( this.dataSource );
    }

    public SqlResultMapper query( final String sql, final Integer limit )
    {
        try (final Handle handle = this.dbi.open())
        {
            final Query<Map<String, Object>> query = handle.createQuery( sql );
            final List<Map<String, Object>> result = limit != null ? query.list( limit ) : query.list();
            return new SqlResultMapper( result );
        }
    }

    public SqlRowMapper queryFirst( final String sql )
    {
        try (final Handle handle = this.dbi.open())
        {
            final Map<String, Object> result = handle.createQuery( sql ).first();
            return new SqlRowMapper( result );
        }
    }

    public int insert( final String sql )
    {
        try (final Handle handle = this.dbi.open())
        {
            return handle.insert( sql );
        }
    }

    public int update( final String sql )
    {
        try (final Handle handle = this.dbi.open())
        {
            return handle.update( sql );
        }
    }

    public void execute( final String sql )
    {
        try (final Handle handle = this.dbi.open())
        {
            handle.execute( sql );
        }
    }

    public void dispose()
    {
        this.dataSource.close();
    }
}
