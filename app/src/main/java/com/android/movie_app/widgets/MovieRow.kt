package com.android.movie_app.widgets

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.android.movie_app.models.Movie
import com.android.movie_app.models.getMovies
import com.android.movie_app.ui.theme.lightGrey
import androidx.compose.material.Typography

@Composable
fun MovieRow(
    movie: Movie = getMovies()[0],
    onClickItem: (String) -> Unit = {}
){

    var isInfoShown by  mutableStateOf(false)

    Card (
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
//            .height(130.dp)
            .clickable {
                onClickItem(movie.id)

                Log.d("Movie ID:", "MovieRow: ${movie.id}")
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
                shape = RoundedCornerShape(16.dp),
                shadowElevation = 4.dp)
            {

                Image(
                    painter = rememberImagePainter(
                        data = movie.images[0],
                        builder = {
                            crossfade(true)
                            transformations(CircleCropTransformation())
                        }
                    ),
                    contentDescription = "Movie Poster",
                    contentScale = ContentScale.Crop,
//                    modifier = Modifier.size(100.dp)
                )
/*
                Icon(imageVector = Icons.Default.AccountBox,
                    contentDescription = "Movie Image",
                    tint = Color.Black)
*/
            }

            Column (modifier = Modifier.padding(4.dp)){
                Text(text = movie.title,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

                Text(text = "Director: ${movie.director}",
                    fontSize = 12.sp)

                Text(text = "Released: ${movie.year}",
                    fontSize = 12.sp)

                AnimatedVisibility(visible = isInfoShown) {

                    Column {
                        Text(
                            buildAnnotatedString {

                                 withStyle(style = SpanStyle(color = Color.DarkGray,
                                     fontSize = 14.sp)){
                                     append("Plot: ")
                                 }

                                withStyle(style = SpanStyle(color = Color.DarkGray,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Light)){
                                    append(movie.plot)
                                }

                            },  modifier = Modifier.padding(6.dp))

                        Divider(modifier = Modifier.padding(6.dp))

                        Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.labelSmall)
                        Text(text = "Actors: ${movie.actors}", style = MaterialTheme.typography.labelSmall)
                        Text(text = "Rating: ${movie.rating}", style = MaterialTheme.typography.labelSmall)
                    }
                }
                Icon(imageVector = if (!isInfoShown) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                    contentDescription = "Down Arrow",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            isInfoShown = !isInfoShown
                        },
                    tint = Color.DarkGray
                )
            }
        }
    }
}
