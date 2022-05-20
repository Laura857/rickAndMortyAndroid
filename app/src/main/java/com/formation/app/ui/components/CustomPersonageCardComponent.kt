import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.formation.app.common.ui.theme.HarderPink

@Composable
fun CustomPersonageCardComponent(
    name: String,
    image: String?,
    status: String,
    origin: String,
    onClick: () -> Unit,
) {
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
                        .padding(8.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.HarderPink, CircleShape),
                    contentScale = ContentScale.Fit,
                )
            }
            Column(Modifier.padding(8.dp)) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.h5,
                    color = Color.HarderPink,
                )

                Text(text = "Origin : $origin")

                Row() {
                    if (status != "unknown") {
                        Text(text = "Status : ")
                        if (status == "Alive") {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "Alive-icon",
                                modifier = Modifier.size(20.dp),
                                tint = Color.Red
                            )
                        } else if (status == "Dead") {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "Dead-icon",
                                modifier = Modifier.size(20.dp),
                                tint = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}