package com.example.tips_for_each_day.data

import android.content.Context
import androidx.annotation.StringRes
import com.example.tips_for_each_day.R

object TipsRepository
{
    fun getTips(context: Context): List<Tip>
    {
        val _tips: ArrayList<Tip> = ArrayList()
        for (i in 1..15)
        {
            val title: StringBuilder = StringBuilder()
            title.append("day_")
            title.append(i)
            title.append("_title")

            val description: StringBuilder = StringBuilder()
            description.append("day_")
            description.append(i)
            description.append("_description")

            @StringRes val titleId: Int = context.resources.getIdentifier(title.toString(), "string", context.packageName)
            @StringRes val descriptionId: Int = context.resources.getIdentifier(description.toString(), "string", context.packageName)

            _tips.add(Tip(titleId, R.drawable.water_test, descriptionId))
        }
        return _tips
    }
}