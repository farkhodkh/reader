package ru.farkhodkhaknazarov.pickabureader.api.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostItem(
    @PrimaryKey
    val id: Int = 0,
    val title: String?,
    val body: String?,
    val images: List<String>?,
    var saved: Boolean = false
){
    constructor(): this(0, "", "", emptyList(), false)

    override fun hashCode(): Int {
        return id
    }

    override fun equals(other: Any?): Boolean {
        if (other is PostItem) {
            return this.id == other.id
                    && this.title == other.title
                    && this.body == other.body
        } else {
            return false
        }
    }
}