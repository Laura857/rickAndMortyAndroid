import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.formation.app.common.ui.theme.MainPink

@Composable
fun CustomTextFieldComponent(
    inputValue: String,
    title: String,
    placeholder: String,
    hidden: Boolean = false,
    icon: ImageVector,
    modifier: Modifier,
    setFunction: (String) -> Unit
) {
    TextField(
        value = inputValue,
        label = { Text(text = title, color = Color.Black, fontSize = 12.sp) },
        onValueChange = { setFunction(it) },
        modifier = modifier,
        leadingIcon = { Icon(icon, "inputIcon") },
        shape = RoundedCornerShape(50),
        visualTransformation = if (hidden) PasswordVisualTransformation() else VisualTransformation.None,
        placeholder = { Text(text = placeholder) },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.MainPink,
            cursorColor = Color.Black,
            disabledLabelColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
    )
}