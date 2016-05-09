package com.enonic.lib.sql;

import java.util.List;
import java.util.Map;

import com.enonic.xp.script.serializer.MapGenerator;
import com.enonic.xp.script.serializer.MapSerializable;

public final class SqlResultMapper
    implements MapSerializable
{
    private final List<Map<String, Object>> result;

    public SqlResultMapper( final List<Map<String, Object>> result )
    {
        this.result = result;
    }

    @Override
    public void serialize( final MapGenerator gen )
    {
        gen.value( "count", this.result.size() );
        gen.array( "result" );

        for ( final Map<String, Object> row : this.result )
        {
            serializeRow( gen, row );
        }

        gen.end();
    }

    private void serializeRow( final MapGenerator gen, final Map<String, Object> row )
    {
        gen.map();
        new SqlRowMapper( row ).serialize( gen );
        gen.end();
    }
}
