package com.example.testapplication.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "accounts",
    indices = [
        Index("email", unique = true)
    ]
)
data class AccountDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "email", collate = ColumnInfo.NOCASE) val email: String,
    val username: String,
    val password: String,
    @ColumnInfo(name = "created_at") val createdAt: Long
) {

    fun toAccount(): Account = Account(
        id = id,
        username = username,
        email = email,
        createdAt = createdAt
    )

    companion object {
        fun fromSignUpData(): AccountDbEntity {
            TODO()
        }
    }
}