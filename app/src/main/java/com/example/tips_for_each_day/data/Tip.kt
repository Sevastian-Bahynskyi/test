package com.example.tips_for_each_day.data

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringDef
import androidx.annotation.StringRes
import com.example.tips_for_each_day.R

data class Tip(
    @StringRes val titleId: Int,
    @DrawableRes val imageId: Int,
    @StringRes val descriptionId: Int)