import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.formation.app.common.ui.theme.HarderPink

@Composable
fun CustomIconTextComponent(
    title: String,
    iconName: ImageVector,
) {
    Row(modifier = Modifier.padding(horizontal = 2.dp, vertical = 5.dp)) {
        Icon(
            imageVector = iconName,
            contentDescription = "icon",
            modifier = Modifier.size(20.dp),
            tint = Color.HarderPink
        )
        Text(" $title")
    }
}