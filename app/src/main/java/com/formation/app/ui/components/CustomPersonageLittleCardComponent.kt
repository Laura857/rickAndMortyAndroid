import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun CustomPersonageLittleCardComponent(
    name: String,
    gender: String,
    image: String,
    onClick: () -> Unit,
) {
    Card(modifier = Modifier
        .padding(8.dp)
        .clickable { onClick.invoke() }) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = rememberImagePainter(
                    data = Uri.parse(image)
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp),
                alpha = 0.5F,
            )
            Column(modifier = Modifier
                .padding(10.dp)
                .align(alignment = Alignment.BottomStart)) {
                Text(
                    text = gender,
                )
                Text(
                    text = name,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}