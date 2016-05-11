/**
 * @namespace sql
 */

function required(params, name) {
    var value = params[name];
    if (value === undefined) {
        throw "Parameter '" + name + "' is required";
    }

    return value;
}

function nullOrValue(value) {
    if (value === undefined) {
        return null;
    }

    return value;
}

function Handle(native) {
    this.native = native;
}

Handle.prototype.query = function (sql, limit) {
    if (!limit) {
        limit = null;
    }

    return __.toNativeObject(this.native.query(sql, limit));
};

Handle.prototype.queryFirst = function (sql) {
    return __.toNativeObject(this.native.queryFirst(sql));
};

Handle.prototype.insert = function (sql) {
    return this.native.insert(sql);
};

Handle.prototype.update = function (sql) {
    return this.native.update(sql);
};

Handle.prototype.execute = function (sql) {
    this.native.execute(sql);
};

/**
 * Connects to a database.
 *
 * @param params
 * @returns {Handle}
 */
exports.connect = function (params) {
    var factory = __.newBean('com.enonic.lib.sql.SqlHandleFactory');
    factory.url = required(params, 'url');
    factory.driver = required(params, 'driver');
    factory.user = params.user;
    factory.password = params.password;
    return new Handle(factory.create());
};
