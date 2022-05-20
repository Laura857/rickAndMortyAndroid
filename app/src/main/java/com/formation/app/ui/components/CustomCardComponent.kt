import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.formation.app.common.ui.theme.HarderPink

@Composable
fun CustomCardComponent(name: String, image: String?, subtitle: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onClick.invoke() },
        shape = RoundedCornerShape(20.dp),
        elevation = 5.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (image != null) {
                Image(
                    painter = rememberImagePainter(
                        data = Uri.parse(image)
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(130.dp)
                        .padding(8.dp),
                    contentScale = ContentScale.Fit,
                )
            }
            Column(Modifier.padding(15.dp)) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.h5,
                    color = Color.HarderPink,
                )
                Text(text = subtitle)
            }
        }
    }
}