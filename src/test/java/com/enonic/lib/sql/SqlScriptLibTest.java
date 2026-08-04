package com.enonic.lib.sql;

import com.enonic.xp.testing.ScriptRunnerSupport;

public class SqlScriptLibTest
    extends ScriptRunnerSupport
{
    @Override
    protected void initialize()
        throws Exception
    {
        super.initialize();
        addService( SqlHandleRegistry.class, new SqlHandleRegistry() );
    }

    @Override
    public String getScriptTestFile()
    {
        return "/lib/sql-test.js";
    }
}
