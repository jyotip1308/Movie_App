package com.android.movie_app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.movie_app.navigation.MovieNavigation
import com.android.movie_app.ui.theme.Movie_AppTheme
import com.android.movie_app.ui.theme.lightGrey

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MovieNavigation()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable (PaddingValues) -> Unit) {
    Movie_AppTheme {
        content(PaddingValues())

    }
}



@Composable
fun MovieRow(movie: String,
                onClickItem: (String) -> Unit
             ){
        Card (
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .height(130.dp)
                .clickable {
                    onClickItem(movie)
                },
            colors = CardDefaults.cardColors(lightGrey),
            shape = RoundedCornerShape(16.dp)
        ){
            Row (verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start){
                Surface (modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                    color = lightGrey,
                    shape = RectangleShape,
                    shadowElevation = 4.dp)
                {
                    Icon(imageVector = Icons.Default.AccountBox,
                        contentDescription = "Movie Image",
                        tint = Color.Black)
                }
                Text(text = movie)
            }                    
        }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApp {
        MovieNavigation()
    }
}


