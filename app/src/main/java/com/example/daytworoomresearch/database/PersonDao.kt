package com.example.daytworoomresearch.database

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.daytworoomresearch.feature.shared.model.Person
import com.example.daytworoomresearch.utils.constants.DatabaseConstants

@Dao
interface PersonDao {
    @Insert
    fun insertPerson (person : Person)

    @Query("SELECT * FROM ${DatabaseConstants.personTable}")
    fun getAllPerson() : List<Person>

    @Query ("SELECT * FROM ${DatabaseConstants.personTable} WHERE id = :personId")
    fun getPerson (personId: Int) : Person

    @Query ("DELETE FROM ${DatabaseConstants.personTable} WHERE id = :personId ")
    fun deletePerson (personId : Int)

    @Update (onConflict = REPLACE)
    fun updatePerson (person : Person)

}