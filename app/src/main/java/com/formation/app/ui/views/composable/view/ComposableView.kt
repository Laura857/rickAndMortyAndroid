import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.formation.app.ui.views.composable.viewModels.ComposableViewModel
import com.formation.app.services.appData.UserService
import com.formation.app.ui.views.navigation.AppControllerView

@Composable
fun ComposableView(composableViewModel: ComposableViewModel = hiltViewModel()) {
    val authState by composableViewModel.authState.observeAsState()

    when (authState) {
        UserService.AuthState.UnSplash -> AppControllerView()
        UserService.AuthState.Splash -> SplashView()
    }

}
