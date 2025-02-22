package com.example.countriesapp.screens

//import androidx.compose.material.Scaffold

import com.example.countriesapp.viewModel.Country
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.countriesapp.navigation.CountryScreens
import com.example.countriesapp.viewModel.getCountries

@Composable
fun HomeScreen(
   navController: NavController,
   countryList: List<Country> = getCountries()
) {

    Column (modifier = Modifier.padding(12.dp)){
        LazyColumn{
            items(items = countryList) {
                MovieRow(country = it) { country ->
                    navController.navigate(route = CountryScreens.DetailScreen.name + "/$country")
                }
            }
        }
    }
}

@Preview
@Composable
fun MovieRow(country: Country = getCountries()[0], itemClick:(String) -> Unit = {} ) {

    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        .clickable {
            //add itemclick
            itemClick(country.name)
        },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RectangleShape,
            ) {
                Image(
                    painter = rememberImagePainter(data = country.flag),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Movie Poster"
                )


            }
            Column(modifier = Modifier.padding(4.dp)) {
                Text(
                    text = country.name,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "Capital: ${country.capital}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Currency: ${country.currency}",
                    style = MaterialTheme.typography.bodyLarge
                )


            }
        }



    }


}