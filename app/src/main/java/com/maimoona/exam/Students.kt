package com.maimoona.exam

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
class Students  (@PrimaryKey val id: UUID = UUID.randomUUID(),
                 var stdNo: Int=0,
                 var stdNmae: String = "",
                 var stdPass:Boolean=false){
}