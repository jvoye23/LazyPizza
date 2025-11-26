package com.jv23.lazypizza.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.jv23.lazypizza.core.database.entity.ToppingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ToppingDao {
    @Upsert
    suspend fun upsertToppings(toppings: List<ToppingEntity>)

    @Query("SELECT * FROM toppings ORDER BY id ASC")
    fun getToppings(): Flow<List<ToppingEntity>>

    @Query("SELECT * FROM toppings WHERE id = :id")
    suspend fun getToppingById(id: String): ToppingEntity?

    @Query("DELETE FROM toppings")
    suspend fun deleteAllToppings()
}