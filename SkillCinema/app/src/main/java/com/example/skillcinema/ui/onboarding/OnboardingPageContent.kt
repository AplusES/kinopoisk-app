package com.example.skillcinema.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.skillcinema.R
import com.example.skillcinema.data.repository.OnboardingList
import com.example.skillcinema.data.models.OnboardingItem

/**
 * Composable функция, отображающая содержимое одного экрана онбординга.
 *
 * @param item Элемент онбординга, содержащий данные для отображения
 * @param onSkipClicked Callback, вызываемый при нажатии на кнопку "Пропустить"
 */
@Composable
fun OnboardingPageContent(
    item: OnboardingItem,
    onSkipClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = dimensionResource(R.dimen.padding_16dp),
                horizontal = dimensionResource(R.dimen.padding_8dp)
            ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.name_application),
                style = TextStyle(
                    fontSize = dimensionResource(R.dimen.text_size_large).value.sp
                )
            )
            Text(
                text = stringResource(R.string.skip),
                modifier = Modifier
                    .clickable (onClick = onSkipClicked),
                style = TextStyle(
                    fontSize = dimensionResource(R.dimen.text_size_medium).value.sp,
                    color = Color.Black.copy(alpha = 0.5f)
                )
            )
        }


        Image(
            painter = painterResource(id = item.image),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        )

        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = dimensionResource(R.dimen.padding_bottom_onboard)),
            text = stringResource(id = item.description),
            style = TextStyle(
                fontSize = dimensionResource(R.dimen.text_size_large).value.sp
            )
        )
    }


}

@Preview(showBackground = true)
@Composable
fun preveiwOnboardingScreen() {
    OnboardingPageContent(
        OnboardingList.items[0],
        onSkipClicked = {}
    )
}

