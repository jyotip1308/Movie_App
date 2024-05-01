package com.android.movie_app.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.android.movie_app.models.Movie
import com.android.movie_app.models.getMovies
import com.android.movie_app.navigation.MovieScreens
import com.android.movie_app.widgets.MovieRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navHostController: NavHostController){

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(Color.Transparent),
                title = { Text(text = "Movies",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                ) }
            )
        }) { paddingValues ->
        MainContent(navHostController,paddingValues)
    }
}
@Composable
fun MainContent(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    movieList: List<Movie> = getMovies())
{

    Column(modifier = Modifier.padding(12.dp)) {

        Surface(
            modifier = Modifier.padding(paddingValues),
            color = MaterialTheme.colorScheme.background
        ) {
            LazyColumn {
                items(items = movieList){

                    MovieRow(movie = it){ movie->
                        navHostController.navigate(route = MovieScreens.DetailsScreen.name+"/$movie")
                    }
                }
            }
        }
    }
}
