package com.example.myapplication

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
//    actual fun createDriver(): SqlDriver {
//        AppBa
//        return NativeSqliteDriver(SqlDriver.Schema, "test.db")
//
//    }

    fun createDriver(): SqlDriver {
        return NativeSqliteDriver(AppDatabase.Schema, "test.db")
    }
}