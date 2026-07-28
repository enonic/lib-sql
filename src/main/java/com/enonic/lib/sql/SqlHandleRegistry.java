package com.enonic.lib.sql;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

@Component(immediate = true, service = SqlHandleRegistry.class)
public class SqlHandleRegistry
{
    private final List<SqlHandle> handles = new CopyOnWriteArrayList<>();

    public void add( final SqlHandle handle )
    {
        this.handles.add( handle );
    }

    @Deactivate
    public void dispose()
    {
        final List<SqlHandle> current = List.copyOf( this.handles );
        this.handles.clear();
        current.forEach( SqlHandle::dispose );
    }
}
