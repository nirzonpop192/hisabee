package com.faisal.hiasbe.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tbTodo")
@Parcelize
data class Item(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var text: String,
    var passed: Boolean,
    var dueDate: String,
    var status: Status

    ): Parcelable {
    constructor() : this(
        0, "",
        "",
        false,
        "",
        Status.TODO
    )
}
