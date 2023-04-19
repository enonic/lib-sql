var sqlLib = require('/lib/sql');
var assert = require('/lib/xp/testing');

function connect() {
    return sqlLib.connect({
        url: 'jdbc:h2:file:./build/tmp/data/db',
        driver: 'org.h2.Driver',
        user: 'sa',
        password: 'password'
    });
}

function createTable(handle) {
    handle.execute('DROP TABLE IF EXISTS test');
    handle.execute('CREATE TABLE test (`key` integer, name varchar(32))');
}

function insertData(handle) {
    handle.insert("INSERT INTO test VALUES (1, 'enonic')");
    handle.insert("INSERT INTO test VALUES (2, 'bekk')");
    handle.insert("INSERT INTO test VALUES (3, 'evry')");
}

function connectAndPrepare() {
    var handle = connect();
    createTable(handle);
    insertData(handle);
    return handle;
}

exports.testQuery = function () {
    var handle = connectAndPrepare();
    var result = handle.query('SELECT * FROM test');

    var expected = {
        "count": 3,
        "result": [
            {
                "name": "enonic",
                "key": 1
            },
            {
                "name": "bekk",
                "key": 2
            },
            {
                "name": "evry",
                "key": 3
            }
        ]
    };

    assert.assertJsonEquals(expected, result);
    sqlLib.dispose();
};

exports.testQueryLimit = function () {
    var handle = connectAndPrepare();
    var result = handle.query('SELECT * FROM test', 2);

    var expected = {
        "count": 2,
        "result": [
            {
                "name": "enonic",
                "key": 1
            },
            {
                "name": "bekk",
                "key": 2
            }
        ]
    };

    assert.assertJsonEquals(expected, result);
    sqlLib.dispose();
};

exports.testQueryFirst = function () {
    var handle = connectAndPrepare();
    var result = handle.queryFirst('SELECT count(*) as num FROM test');

    var expected = {
        "num": 3
    };

    assert.assertJsonEquals(expected, result);
    sqlLib.dispose();
};

exports.testUpdate = function () {
    var handle = connectAndPrepare();

    var result1 = handle.update("UPDATE test SET name='other' WHERE `key` = 1");
    assert.assertEquals(1, result1);

    var result2 = handle.update("UPDATE test SET name='other' WHERE `key` > 1");
    assert.assertEquals(2, result2);
    sqlLib.dispose();
};
