import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.formation.app.R
import com.formation.app.ui.views.splash.viewModel.SplashViewModel

@Composable
fun SplashView(splashViewModel: SplashViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = Unit, block = {
        splashViewModel.fetchSplashData()
    })
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(R.drawable.rickandmortysplash),
            contentDescription = "splash",
            contentScale = ContentScale.FillBounds
        )
    }
}
