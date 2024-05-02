package com.android.movie_app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.android.movie_app.models.Movie
import com.android.movie_app.models.getMovies
import com.android.movie_app.widgets.MovieRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navHostController: NavHostController, movieId: String?){

    val newMovieList = getMovies().filter { movie ->
       movie.id == movieId
    }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Row (horizontalArrangement = Arrangement.Start){
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow Back",
                        modifier = Modifier.clickable {
                            navHostController.popBackStack()
                        })

                    Spacer(modifier = Modifier.width(26.dp))
                    Text(text = "Movies",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        )
                    )
                }

            },
            colors = TopAppBarDefaults.topAppBarColors(Color.Transparent)
        )
    } ) {it

        Surface (modifier = Modifier
            .padding(top = 64.dp)
            .fillMaxWidth()
            .fillMaxHeight()){
            Column(verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {


                MovieRow(movie = newMovieList.first())
                Spacer(modifier = Modifier.height(16.dp))

                Divider()

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Movie Images",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold)

                HorizontalScrollImageView(newMovieList)
            }
        }
    }
}

@Composable
private fun HorizontalScrollImageView(newMovieList: List<Movie>) {
    LazyRow {
        items(newMovieList[0].images) { image ->

            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp)
            ) {
                Row(modifier = Modifier.fillMaxHeight()
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = rememberImagePainter(data = image),
                        contentDescription = "Images"
                    )
                }

            }

        }
    }
}