# Types for SQL library

Type definitions for the [Enonic XP SQL library](https://github.com/enonic/lib-sql).

## Install

```bash
npm i --save-dev @enonic-types/lib-sql
```

## Setup

Add lib-sql to `compilerOptions.paths` in the `tsconfig.json` file used for your application's server-side TypeScript code:

```json
{
  "compilerOptions": {
    "paths": {
      "/lib/sql": ["./node_modules/@enonic-types/lib-sql"]
    }
  }
}
```

## Use

```ts
import {connect} from '/lib/sql';

const handle = connect({
    url: 'jdbc:h2:file:./build/tmp/data/db',
    driver: 'org.h2.Driver',
    user: 'sa',
    password: 'password',
    maxPoolSize: 10,
    minPoolSize: 0,
});

export function get() {
    const result = handle.query('SELECT * FROM persons');

    return {
        body: result,
        contentType: 'application/json',
    };
}
```

All imports from `/lib/sql` should now be typed.

## API

### `connect(params: ConnectParams): Handle`

Opens a connection to a SQL database and returns a handle for issuing queries and statements.

### `dispose(): void`

Disposes of all open connections created by this library within the current application.

### `ConnectParams`

| Property      | Type     | Description                                                          |
|---------------|----------|----------------------------------------------------------------------|
| `url`         | `string` | JDBC connection URL, e.g. `jdbc:h2:file:./build/tmp/data/db`. **Required.** |
| `driver`      | `string` | Fully-qualified JDBC driver class name, e.g. `org.h2.Driver`. **Required.** |
| `user`        | `string` | Database user.                                                       |
| `password`    | `string` | Database password.                                                   |
| `maxPoolSize` | `number` | Maximum number of connections in the pool. Default `10`.             |
| `minPoolSize` | `number` | Minimum number of idle connections kept in the pool. Default `0`.    |
| `poolName`    | `string` | Pool name used for logging and monitoring.                           |

### `Handle`

| Method                                                        | Description                                                                                  |
|---------------------------------------------------------------|----------------------------------------------------------------------------------------------|
| `query(sql: string, limit?: number \| null): QueryResult`     | Executes a `SELECT` query and returns all matching rows, optionally limited to `limit` rows. |
| `queryFirst(sql: string): Record<string, unknown> \| null`    | Executes a `SELECT` query and returns the first matching row, or `null` if there are none.   |
| `insert(sql: string): number`                                 | Executes an `INSERT` statement and returns the number of rows affected.                      |
| `update(sql: string): number`                                 | Executes an `UPDATE` statement and returns the number of rows affected.                      |
| `execute(sql: string): void`                                  | Executes a statement that does not return a result, e.g. `CREATE TABLE` or `DROP TABLE`.     |

### `QueryResult`

| Property | Type                        | Description                                     |
|----------|-----------------------------|-------------------------------------------------|
| `count`  | `number`                    | Number of rows returned.                        |
| `result` | `Record<string, unknown>[]` | The result rows, each row keyed by column name. |

## JDBC drivers

The library does not bundle any JDBC drivers. Include the driver for your database in your application's `build.gradle`:

```gradle
dependencies {
    include 'com.enonic.lib:lib-sql:<version>'
    include 'org.postgresql:postgresql:<version>'
}
```

## Documentation

[SQL library](https://github.com/enonic/lib-sql#readme)
