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
    constructor(title: String,dueDate: String,status: Boolean) : this(
        "",
        "",
        false,
    0
    )
}
