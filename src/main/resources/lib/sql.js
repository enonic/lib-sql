/**
 * @namespace sql
 */

var factory = __.newBean('com.enonic.lib.sql.SqlHandleFactory');

function required(params, name) {
    var value = params[name];
    if (value === undefined) {
        throw "Parameter '" + name + "' is required";
    }

    return value;
}

function optional(params, name, defValue) {
    var value = params[name];
    if (value === undefined) {
        return defValue;
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

exports.connect = function (params) {
    var source = __.newBean('com.enonic.lib.sql.SqlSource');
    source.url = required(params, 'url');
    source.driver = required(params, 'driver');
    source.maxPoolSize = optional(params, 'maxPoolSize', 10);
    source.minPoolSize = optional(params, 'minPoolSize', 0);
    source.poolName = optional(params, 'poolName', null);
    source.user = params.user;
    source.password = params.password;
    return new Handle(factory.create(source));
};

function dispose() {
    factory.dispose();
}

exports.dispose = dispose;
__.disposer(dispose);
