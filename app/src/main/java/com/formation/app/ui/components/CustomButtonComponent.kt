
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun CustomButtonComponent(
    title: String,
    modifier: Modifier,
    backgroundColor: Color,
    myFunction: () -> Unit
) {
    Button(
        onClick = { myFunction() },
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        modifier = modifier,
        shape = RoundedCornerShape(50),
    )

    {
        Text(text = title, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }
}