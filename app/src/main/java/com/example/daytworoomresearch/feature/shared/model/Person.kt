package com.example.daytworoomresearch.feature.shared.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.daytworoomresearch.utils.constants.DatabaseConstants

@Entity(tableName = DatabaseConstants.personTable )
data class Person(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,

    @ColumnInfo(name  = "name")
    var name : String? = null,

    @ColumnInfo(name = "address")
    var address : String? = null,

    @ColumnInfo(name = "phoneNumber")
    var phoneNumber : String? = null

)