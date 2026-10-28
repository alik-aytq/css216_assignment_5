package com.example.buildgrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.buildgrid.ui.theme.BuildGridTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.ui.Alignment
import androidx.compose.material3.Icon
import androidx.compose.foundation.lazy.grid.items

class MainActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContent{
            MaterialTheme{
                CoursesGrid()
            }
        }
    }
}

@Composable
fun TopicGridItem(
    topic: Topic,
    modifier: Modifier=Modifier
){
    Card(
        modifier=modifier,
        colors= CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ){
        Row {
            Image(
                painter = painterResource(topic.images),
                contentDescription = stringResource(topic.name),
                modifier = Modifier.size(68.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier= Modifier.padding(
                    start = 16.dp,
                    end=16.dp
                )
            ){
                Text(
                    text=stringResource(topic.name),
                    modifier=Modifier.padding(top=16.dp),
                    style= MaterialTheme.typography.bodyMedium
                )

                Row(
                    modifier=Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter=painterResource(R.drawable.ic_grain),
                        contentDescription = null,
                        modifier=Modifier.size(16.dp)
                    )

                    Text(
                        text=topic.numberOfCourses.toString(),
                        modifier=Modifier.padding(start=8.dp),
                        style=MaterialTheme.typography.labelMedium
                    )

                }
            }
        }
    }
}

@Composable
fun CoursesGrid(modifier: Modifier= Modifier){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier=modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        items(DataSource.topics) {topic ->
            TopicGridItem(topic = topic)
        }
    }
}

data class Topic(
    @StringRes val name: Int,
    val numberOfCourses: Int,
    @DrawableRes val images: Int
)