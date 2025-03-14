package com.example.airqualityapp.screens

import android.annotation.SuppressLint
import android.content.Context
//import androidx.activity.ComponentActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.airqualityapp.utils.getBackgroundGradient
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.airqualityapp.R
import com.example.airqualityapp.ui.theme.MediumGreen
import com.example.airqualityapp.utils.slidePages


@SuppressLint("ContextCastToActivity")
@Composable
fun OnboardingScreen(
    curPage: Int = 0,
    navController: NavHostController = rememberNavController(),
    context: Context? = null
) {
    val actualContext = context ?: LocalContext.current // Usa o contexto passado ou obtém um

    var currentPage by remember { mutableIntStateOf(curPage) }

    // Salvar no SharedPreferences que o onboarding já foi visto
    fun saveOnboardingCompleted(context: Context) {
        val sharedPrefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        sharedPrefs.edit().putBoolean("onboarding_completed", false).apply()
        navController.navigate("home") {
            popUpTo("onBoarding") { inclusive = true }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(1f)
            .background(getBackgroundGradient()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                //.offset(y = 45.dp)
                .padding(horizontal = 26.dp)
                .padding(bottom = 20.dp)
                .height(110.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 15.dp),
                text = slidePages[currentPage].title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.DarkGray
            )
        }
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .offset(x = (-1).dp)
                .padding(vertical = 20.dp),
            painter = painterResource(
                when (currentPage) {
                    0 -> R.drawable.initiate_1
                    1 -> R.drawable.initiate_2
                    2 -> R.drawable.initiate_3
                    else -> R.drawable.initiate_4
                }
            ), // Substitua pelo nome da sua imagem
            contentDescription = "onboarding",
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Indicador de progresso (bolinhas)
            Row(
                modifier = Modifier.padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                slidePages.forEachIndexed { index, _ ->
                    val isSelected = index == currentPage
                    Box(
                        modifier = Modifier
                            .size(if (isSelected) 30.dp else 26.dp)
                            .padding(6.dp)
                            .background(
                                color = if (isSelected) MediumGreen else Color.White,
                                shape = CircleShape
                            ).clickable {
                                currentPage = index
                            }
                    )
                }
            }

            // Botões de controle
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AnimatedVisibility(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    visible = currentPage != 0
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MediumGreen,
                            contentColor = Color.White
                        ),
                        onClick = { currentPage-- }) {
                        Text("Anterior")
                    }
                }
                AnimatedVisibility(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    visible = currentPage < slidePages.size - 1
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MediumGreen,
                            contentColor = Color.White
                        ),
                        onClick = { currentPage++ }) {
                        Text("Próximo")
                    }
                }
                AnimatedVisibility(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    visible = currentPage == 0 || currentPage == slidePages.size - 1
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = MediumGreen
                        ),
                        onClick = {
                            saveOnboardingCompleted(actualContext)
                            //(actualContext as? ComponentActivity)?.finish()
                        }) {
                        Text(text = if (currentPage != slidePages.size - 1) "Pular" else "Vamos Começar")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreen1Preview() {
    OnboardingScreen()
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreen2Preview() {
    OnboardingScreen(1)
}
@Preview(showBackground = true)
@Composable
fun OnboardingScreen3Preview() {
    OnboardingScreen(2)
}
@Preview(showBackground = true)
@Composable
fun OnboardingScreen4Preview() {
    OnboardingScreen(3)
}