package com.enonic.lib.sql;

import java.util.List;

import com.google.common.collect.Lists;

public final class SqlHandleFactory
{
    private final List<SqlHandle> handles;

    public SqlHandleFactory()
    {
        this.handles = Lists.newArrayList();
    }

    public void dispose()
    {
        this.handles.forEach( SqlHandle::dispose );
        this.handles.clear();
    }

    public SqlHandle create( final SqlSource source )
        throws Exception
    {
        final SqlHandle handle = new SqlHandle( source.newDataSource() );
        this.handles.add( handle );
        return handle;
    }
}
