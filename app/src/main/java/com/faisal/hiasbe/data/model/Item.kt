package com.faisal.hiasbe.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tbTodo")
@Parcelize
data class Item(

    var title: String,
    @PrimaryKey
    var dueDate: String,
    var status: Boolean,

    ): Parcelable {
    constructor() : this(
        "",
        "",
        false,

    )


}
