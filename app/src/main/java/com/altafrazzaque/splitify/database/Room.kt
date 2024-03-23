package com.altafrazzaque.splitify.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.altafrazzaque.splitify.model.ExpenseItem

@Dao
interface ExpenseDao {
    @Query("select * from ExpenseItem ORDER BY date DESC")
    fun getExpenses(): LiveData<List<ExpenseItem>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<ExpenseItem>)

    @Query("select * from ExpenseItem WHERE id LIKE :id")
    fun getHistoryDetails(id: String): LiveData<ExpenseItem?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: ExpenseItem)

    @Query("SELECT SUM(price) FROM ExpenseItem WHERE strftime('%m', date) = strftime('%m', 'now') AND strftime('%Y', date) = strftime('%Y', 'now') ORDER BY date DESC")
    fun getTotalThisMonth(): LiveData<Double?>
}

@Database(entities = [ExpenseItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val expenseDao: ExpenseDao
}