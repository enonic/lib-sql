package com.enonic.lib.sql;

import com.enonic.xp.testing.script.ScriptRunnerSupport;

public class SqlScriptLibTest
    extends ScriptRunnerSupport
{
    @Override
    public String getScriptTestFile()
    {
        return "site/lib/sql-test.js";
    }
}
