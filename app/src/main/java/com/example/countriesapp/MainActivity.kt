package com.example.countriesapp

import Country
import CountryList
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.countriesapp.ui.theme.CountriesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CountriesAppTheme {
                Surface() {
                    CountryApp()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CountryAppPreview() {
    CountriesAppTheme {
        CountryApp()
    }
}
@Composable
fun CountryApp() {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
    ){
        items(CountryList) { item: Country ->
            CountryElement(item)
        }
    }
}

@Composable
fun CountryElement(
    country: Country,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.background(Color.White).fillMaxWidth()
    ) {
        Image(painterResource(country.flag), contentDescription = "${country.name} Flag", modifier = Modifier.height(50.dp).width(80.dp))
        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
            Text(text = "Country: ${country.name}", style = MaterialTheme.typography.titleMedium, modifier = Modifier)
            Text(text = "Capital: ${country.capital}", style = MaterialTheme.typography.bodySmall, modifier = Modifier)
            Text(text = "Currency: ${country.currency}", style = MaterialTheme.typography.bodySmall, modifier = Modifier)
        }
    }
    Spacer(modifier = Modifier.padding(top = 4.dp))
    HorizontalDivider(thickness = 2.dp, color = Color.Black, modifier = Modifier)
}