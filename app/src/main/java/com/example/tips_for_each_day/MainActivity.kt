@file:OptIn(ExperimentalAnimationApi::class)

package com.example.tips_for_each_day

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.animation.*
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tips_for_each_day.data.Tip
import com.example.tips_for_each_day.data.TipsRepository
import com.example.tips_for_each_day.ui.theme.TipsforeachdayTheme

class MainActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContent {
            TipsforeachdayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TipList(context = this)
                }
            }
        }
    }
}

@Composable
fun TipList(context: Context)
{
    LazyColumn{
        itemsIndexed(TipsRepository.getTips(context = context))
        {
            index, item ->
            if(index % 2 == 0)
                TipItem(day = "Day ${index + 1}", tip = item,
                    modifier = Modifier.background(MaterialTheme.colors.primary))
            else{
                TipItem(day = "Day ${index + 1}", tip = item,
                    modifier = Modifier.background(MaterialTheme.colors.secondary))
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TipItem(
    day: String,
    tip: Tip,
    modifier: Modifier = Modifier)
{
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(16.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp),
        onClick = {
            isExpanded = !isExpanded
        }
    )
    {
        Column(
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = day,
                style = MaterialTheme.typography.h4,
                color = Color.Gray
            )

            Text(
                text = stringResource(id = tip.titleId),
                modifier = Modifier
                    .padding(vertical = 16.dp)
            )

            Box(modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
            )
            {
                Image(
                    painter = painterResource(id = tip.imageId),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .matchParentSize()
                        .clip(RoundedCornerShape(16.dp)),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
            }
            
            AnimatedContent(
                targetState = isExpanded,
                transitionSpec = {
                    slideInVertically (

                    ) with fadeOut()
                },
                content = {
                    isExpanded->
                    if(isExpanded)
                        TipDescription(descriptionId = tip.descriptionId)
                }
            )
        }
    }
}

@Composable
fun TipDescription(
    @StringRes descriptionId: Int,
    modifier: Modifier = Modifier
)
{
    Box() {
        Text(
            text = stringResource(id = descriptionId)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview()
{
    TipsforeachdayTheme {
        TipItem(day = "Day 1", tip = Tip(R.string.day_1_title, R.drawable.water_test, R.string.day_1_description))
    }
}