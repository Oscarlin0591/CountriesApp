package com.example.countriesapp.screens

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.countriesapp.viewModel.getCountries

@SuppressLint("UnusedMaterial3ScaffoldParameter")
@ExperimentalAnimationApi
@Composable
fun DetailsScreen(navController: NavController, countryName: String?) {
    val countryList = getCountries().filter { country ->
        country.name == countryName
    }
        Column (
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Blue,
                    fontSize = 24.sp)) {
                    append("Facts: ")
                }
                withStyle(style = SpanStyle(color = Color.Blue,
                    fontSize = 20.sp)
                ) {
                    append(countryList[0].fact)
                }
            }, modifier = Modifier.padding(6.dp))
            
            HorizontalDivider(modifier = Modifier.padding(3.dp))
            Text(text = "Currency: ${countryList[0].currency}", style = MaterialTheme.typography.titleLarge)
            Text(text = "Capital: ${countryList[0].capital}", style = MaterialTheme.typography.titleLarge)
            Image(painter = painterResource(countryList[0].map), contentDescription = null, modifier = Modifier.scale(3f).offset(54.dp, 40.dp))
        }
}