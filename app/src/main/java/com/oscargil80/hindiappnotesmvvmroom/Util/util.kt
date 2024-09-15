package com.oscargil80.hindiappnotesmvvmroom.Util

import android.text.format.DateFormat
import java.util.Date



    fun traerFecha (d: Date): CharSequence {
        val notesDate = DateFormat.format("MMM d, yyyy ", d.getTime())
        return notesDate
    }
