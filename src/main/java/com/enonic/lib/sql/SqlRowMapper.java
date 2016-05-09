package com.enonic.lib.sql;

import java.util.Map;

import com.enonic.xp.script.serializer.MapGenerator;
import com.enonic.xp.script.serializer.MapSerializable;

public final class SqlRowMapper
    implements MapSerializable
{
    private final Map<String, Object> row;

    public SqlRowMapper( final Map<String, Object> row )
    {
        this.row = row;
    }

    @Override
    public void serialize( final MapGenerator gen )
    {
        for ( final Map.Entry<String, Object> entry : row.entrySet() )
        {
            gen.value( entry.getKey(), entry.getValue() );
        }
    }
}
