package com.example.rentateamtestapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ResultReviews(

    @SerializedName("page")
    val page: Int,

    @SerializedName("per_page")
    val per_page: Int,

    @SerializedName("total")
    val total: Int,

    @SerializedName("total_pages")
    val total_pages: Int,

    @SerializedName("data")
    val data: List<User>
)

@Entity(tableName = "user_table")
data class User(

    @SerializedName("id")
    @field:PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @SerializedName("email")
    @ColumnInfo(name = "email")
    val email: String,

    @SerializedName("first_name")
    @ColumnInfo(name = "first_name")
    val first_name: String,

    @SerializedName("last_name")
    @ColumnInfo(name = "last_name")
    val last_name: String,

    @SerializedName("avatar")
    @ColumnInfo(name = "avatar")
    val avatar: String
)