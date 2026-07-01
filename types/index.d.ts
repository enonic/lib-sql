declare module "/lib/sql" {
    export interface ConnectParams {
        /**
         * JDBC connection URL (e.g. `jdbc:h2:file:./build/tmp/data/db`).
         */
        url: string;
        /**
         * Fully-qualified JDBC driver class name (e.g. `org.h2.Driver`).
         */
        driver: string;
        /**
         * Database user.
         */
        user?: string;
        /**
         * Database password.
         */
        password?: string;
        /**
         * Maximum number of connections in the pool. Defaults to `10`.
         */
        maxPoolSize?: number;
        /**
         * Minimum number of idle connections kept in the pool. Defaults to `0`.
         */
        minPoolSize?: number;
        /**
         * Pool name used for logging and monitoring.
         */
        poolName?: string;
    }

    export interface QueryResult {
        /**
         * Number of rows returned.
         */
        count: number;
        /**
         * The result rows, each row keyed by column name.
         */
        result: Record<string, unknown>[];
    }

    export interface Handle {
        /**
         * Executes a `SELECT` query and returns all matching rows.
         *
         * @param sql SQL query to execute.
         * @param limit Optional maximum number of rows to return.
         */
        query(sql: string, limit?: number | null): QueryResult;

        /**
         * Executes a `SELECT` query and returns the first matching row, or `null` if the result set is empty.
         *
         * @param sql SQL query to execute.
         */
        queryFirst(sql: string): Record<string, unknown> | null;

        /**
         * Executes an `INSERT` statement.
         *
         * @param sql SQL statement to execute.
         * @returns The number of rows affected.
         */
        insert(sql: string): number;

        /**
         * Executes an `UPDATE` statement.
         *
         * @param sql SQL statement to execute.
         * @returns The number of rows affected.
         */
        update(sql: string): number;

        /**
         * Executes a statement that does not return a result (e.g. `CREATE TABLE`, `DROP TABLE`).
         *
         * @param sql SQL statement to execute.
         */
        execute(sql: string): void;
    }

    /**
     * Opens a connection to a SQL database and returns a handle for issuing queries and statements.
     *
     * @param params Connection parameters.
     */
    export function connect(params: ConnectParams): Handle;

    /**
     * Disposes of all open connections created by this library within the current application.
     */
    export function dispose(): void;
}

export {};
