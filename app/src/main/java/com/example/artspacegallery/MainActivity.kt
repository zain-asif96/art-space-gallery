package com.example.artspacegallery

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspacegallery.ui.theme.ArtSpaceGalleryTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceGalleryTheme {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            colors = TopAppBarDefaults.largeTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary, // Gold Background
                                titleContentColor = MaterialTheme.colorScheme.onBackground // White Text
                            ),
                            title = {
                                Text(
                                    stringResource(id = R.string.app_title),
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        )
                    }
                ) {
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it),
                            color = MaterialTheme.colorScheme.background

                        ) {
                            ArtGalleryScreen()
                        }
                }
            }
        }
    }
}

@Composable
fun ArtGalleryScreen(modifier: Modifier = Modifier) {
    val firstArtwork = R.drawable.nelson_mandela
    val secondArtwork = R.drawable.marie_curie
    val thirdArtwork = R.drawable.alexander_fleming
    val fourthArtwork = R.drawable.martin_luther_king_jr

    var fullName by remember {
        mutableIntStateOf(R.string.person1_name)
    }

    var designation by remember {
        mutableIntStateOf(R.string.person1_designation)
    }

    var contribution by remember {
        mutableIntStateOf(R.string.person1_contribution)
    }

    var currentArtwork by remember {
        mutableIntStateOf(firstArtwork)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)

    ) {
        Spacer(modifier = modifier.size(25.dp))
        ArtGalleryCard(
            fullName = fullName,
            profileImage = currentArtwork,
            designation = designation,
            contribution = contribution
        )

        Row(
            modifier = modifier
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        ) {
            // Previous Button
            Button(
                onClick = {
                    when (currentArtwork) {
                        firstArtwork -> {
                            currentArtwork = fourthArtwork
                            fullName = R.string.person4_name
                            designation = R.string.person4_designation
                            contribution = R.string.person4_contribution
                        }
                        secondArtwork -> {
                            currentArtwork = firstArtwork
                            fullName = R.string.person1_name
                            designation = R.string.person1_designation
                            contribution = R.string.person1_contribution
                        }
                        thirdArtwork -> {
                            currentArtwork = secondArtwork
                            fullName = R.string.person2_name
                            designation = R.string.person2_designation
                            contribution = R.string.person2_contribution
                        }
                        else -> {
                            currentArtwork = thirdArtwork
                            fullName = R.string.person3_name
                            designation = R.string.person3_designation
                            contribution = R.string.person3_contribution
                        }
                    }
                },

                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                enabled = currentArtwork != firstArtwork,
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 4.dp,
                    pressedElevation = 0.dp,
                    focusedElevation = 0.dp,
                )
            ) {
                Text(
                    text = "Previous",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                )
            }

            // Next Button
            Button(
                onClick = {
                    when (currentArtwork) {
                        firstArtwork -> {
                            currentArtwork = secondArtwork
                            fullName = R.string.person2_name
                            designation = R.string.person2_designation
                            contribution = R.string.person2_contribution
                        }
                        secondArtwork -> {
                            currentArtwork = thirdArtwork
                            fullName = R.string.person3_name
                            designation = R.string.person3_designation
                            contribution = R.string.person3_contribution
                        }
                        thirdArtwork -> {
                            currentArtwork = fourthArtwork
                            fullName = R.string.person4_name
                            designation = R.string.person4_designation
                            contribution = R.string.person4_contribution
                        }
                        else -> {
                            currentArtwork = firstArtwork
                            fullName = R.string.person1_name
                            designation = R.string.person1_designation
                            contribution = R.string.person1_contribution
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                enabled = currentArtwork != fourthArtwork,
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 4.dp,
                    pressedElevation = 0.dp,
                    focusedElevation = 0.dp
                )
            ) {
                Text(
                    text = "Next",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
        Spacer(modifier = modifier.size(25.dp))
    }

}

@Composable
fun ArtGalleryCard(
    @StringRes fullName: Int,
    @DrawableRes profileImage: Int,
    @StringRes designation: Int,
    @StringRes contribution: Int,
    modifier: Modifier = Modifier
) {
        Image(
            painter = painterResource(id = profileImage),
            contentDescription = "person",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(200.dp)
                .clip(CircleShape)
        )

        Text(
            text = stringResource(id = fullName),
            fontSize = 26.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = stringResource(id = designation),
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.SemiBold,
            modifier = modifier
                .background(
                    colorResource(id = R.color.light_bluish).copy(alpha = 0.9F),
                    shape = RoundedCornerShape(8.dp), // Apply rounded corners
                )
            .padding(10.dp)
        )
        Text(
            text = stringResource(id = contribution),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.primary,
            modifier = modifier
                .padding(10.dp)
        )
    }


@Preview(showBackground = true)
@Composable
fun ArtGalleryPreview() {
    ArtSpaceGalleryTheme {
        ArtGalleryScreen()
    }
}