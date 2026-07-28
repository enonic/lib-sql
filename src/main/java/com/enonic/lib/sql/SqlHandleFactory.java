package com.enonic.lib.sql;

import com.enonic.xp.script.bean.BeanContext;
import com.enonic.xp.script.bean.ScriptBean;

import static java.util.Objects.requireNonNull;

public final class SqlHandleFactory
    implements ScriptBean
{
    private SqlHandleRegistry registry;

    @Override
    public void initialize( final BeanContext context )
    {
        this.registry = requireNonNull( context.getService( SqlHandleRegistry.class ).get() );
    }

    public void dispose()
    {
        this.registry.dispose();
    }

    public SqlHandle create( final SqlSource source )
        throws Exception
    {
        final SqlHandle handle = new SqlHandle( source.newDataSource() );
        this.registry.add( handle );
        return handle;
    }
}
