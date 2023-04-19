package com.enonic.lib.sql;

import java.util.ArrayList;
import java.util.List;

public final class SqlHandleFactory
{
    private final List<SqlHandle> handles;

    public SqlHandleFactory()
    {
        this.handles = new ArrayList<>();
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
