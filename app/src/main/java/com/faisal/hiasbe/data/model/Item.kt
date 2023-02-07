package com.faisal.hiasbe.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tbTodo")
@Parcelize
data class Item(

    var title: String,
    var dueDate: String,
    var status: Boolean,
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    ): Parcelable {
    constructor() : this(
        "",
        "",
        false,
    0
    )

    constructor(mTitle: String, currentDate: String, b: Boolean) : this()
}
