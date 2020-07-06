SQL lib for Enonic XP
=====================

[![Actions Status](https://github.com/enonic/lib-sql/workflows/Gradle%20Build/badge.svg)](https://github.com/enonic/lib-sql/actions)
[![codecov](https://codecov.io/gh/enonic/lib-sql/branch/master/graph/badge.svg)](https://codecov.io/gh/enonic/lib-sql)
[![License](https://img.shields.io/github/license/enonic/lib-sql.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

This is a library for accessing SQL databases from Enonic XP. It uses plain JDBC drivers under the hood and can connect to
any database which there's a suitable driver.


Compatibility
-------------

| Version | XP Version | Dependency                   |
|---------|------------|------------------------------|
| 0.5.0   | 6.3.x      | com.enonic.lib:lib-sql:0.5.0 |
| 0.6.0   | 6.8.x      | com.enonic.lib:lib-sql:0.6.0 |
| 1.0.0   | 7.0.0      | com.enonic.lib:lib-sql:1.0.0 |


Usage
-----

First, you will need to add Enonic repository to the repository list:

```gradle
repositories {
    maven {
        url 'http://repo.enonic.com/public'
    }
}
```

After this, add the following dependency (where ``<version>`` is the actual version to use):

```gradle
dependencies {
    include 'com.enonic.lib:lib-sql:<version>'
}
```

The last step is to include the actual JDBC driver in your app. This is done by adding it as an ``include`` dependency. Here's
an example on how to include the postgresql JDBC driver (it's perfectly legal to add multiple JDBC drivers to your app):

```gradle
dependencies {
    include 'org.postgresql:postgresql:9.4.1208'
}
```

Example
-------

Here's an example on how to use it with an embedded SQL database (H2). First, you will need to add the driver and this lib into your
``build.gradle`` file:

```gradle
dependencies {
    include 'com.enonic.lib:lib-sql:<version>'
    include 'com.h2database:h2:1.4.190'
}
```

You can then use this inside your javascript controller or other parts of your app. Here's an example of using it inside a controller:

```js
// Include the library
var sqlLib = require('/lib/sql');

// Create a handle for the connection to the database
var handle = sqlLib.connect({
    url: 'jdbc:h2:file:./build/tmp/data/db',
    driver: 'org.h2.Driver',
    user: 'sa',
    password: 'password',
    maxPoolSize: 10,
    minPoolSize: 0
});

// Output data from database in controller
exports.get = function(req) {

    // Query the database
    var result = handle.query('SELECT * FROM persons');

    // Return the result as JSON
    return {
        status: 200,
        body: result,
        contentType: 'application/json'
    }

};
```
