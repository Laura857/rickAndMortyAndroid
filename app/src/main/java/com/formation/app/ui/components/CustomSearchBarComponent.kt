import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.formation.app.common.ui.theme.MainGreen

@Composable
fun CustomSearchBarComponent(
    value: String,
    title: String,
    placeholder: String,
    hidden: Boolean = false,
    icon: ImageVector,
    modifier: Modifier,
    setFunction: (String) -> Unit,
    searchFunction: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        CustomTextFieldComponent(
            value,
            title,
            placeholder,
            hidden,
            icon,
            modifier,
            setFunction
        )

        CustomButtonComponent(
            "Search",
            Modifier,
            Color.MainGreen
        ) {
            searchFunction()
        }
    }
}