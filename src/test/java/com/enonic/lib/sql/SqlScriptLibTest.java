package com.enonic.lib.sql;

import com.enonic.xp.testing.ScriptRunnerSupport;

public class SqlScriptLibTest
    extends ScriptRunnerSupport
{
    @Override
    public String getScriptTestFile()
    {
        return "/lib/sql-test.js";
    }
}
