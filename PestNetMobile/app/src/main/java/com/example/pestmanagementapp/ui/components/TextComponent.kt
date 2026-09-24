package com.example.pestmanagementapp.ui.components


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle


@Composable
fun NormalTextComponent(value:String){
    Text(text = value,
        modifier = Modifier.fillMaxWidth().heightIn(),
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.secondary,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )

}

@Composable
fun HeadingTextComponent(value:String){
    Text(text = value,
        modifier = Modifier.fillMaxWidth().heightIn(min = 30.dp),
        style = MaterialTheme.typography.displaySmall,
        color = MaterialTheme.colorScheme.onSurface,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center
    )

}

@Composable
fun TextFieldComponent(
    labelValue:String,
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    supportingText: String? = null
){

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(15.dp))
            .padding(bottom = 20.dp),
        label = {Text(text = labelValue)},
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            //focusedLabelColor = MaterialTheme.colorScheme.onSecondaryContainer,
            focusedIndicatorColor = MaterialTheme.colorScheme.secondaryContainer, // No colour
            unfocusedIndicatorColor = MaterialTheme.colorScheme.surfaceVariant, // No colour
            cursorColor = MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(12.dp),
        value = value,
        onValueChange = onValueChange,
        isError = isError,
        supportingText = {
            supportingText?.let {
                Text(it, color = MaterialTheme.colorScheme.error)
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        leadingIcon = leadingIcon
    )
}

@Composable
fun PasswordTextFieldComponent(
    labelValue: String,
    password: String,
    onPasswordChange: (String) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    supportingText: String? = null
) {
    val passwordVisible = remember {
        mutableStateOf(value = false)
    }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(15.dp)),
        label = {Text(text = labelValue)},
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            //focusedLabelColor = MaterialTheme.colorScheme.onSecondaryContainer,
            focusedIndicatorColor = MaterialTheme.colorScheme.secondaryContainer, // No colour
            unfocusedIndicatorColor = MaterialTheme.colorScheme.surfaceVariant, // No colour
            cursorColor = MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(12.dp),
        value = password,
        onValueChange = onPasswordChange,
        isError = isError,
        supportingText = {
            supportingText?.let {
                Text(it, color = MaterialTheme.colorScheme.error)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        leadingIcon = leadingIcon,
        trailingIcon = {
            IconButton(onClick = {passwordVisible.value = !passwordVisible.value}){
                Icon(
                    imageVector = if (passwordVisible.value)
                        Icons.Default.Visibility

                    else
                        Icons.Default.VisibilityOff,
                    contentDescription = if (passwordVisible.value) "Hide password"
                    else "Show password"
                )
            }
        },
        visualTransformation = if (passwordVisible.value)
        VisualTransformation.None else PasswordVisualTransformation()
    )
}

@Composable
fun CheckBoxComponent(
    value: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onTextSelected: (String) -> Unit
){
    Row (modifier = Modifier
        .fillMaxWidth()
        .heightIn(56.dp).padding(start = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox (checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.scale(0.8f)
        )

        ClickableTextComponent(value = value, onTextSelected)
    }
}


@Composable
fun ClickableTextComponent(
    value: String,
    onTextSelected: (String) -> Unit
) {
    val initialText = "I have read and agree to the "
    val termsOfUseText = "Terms Of Use"
    val andText = " and "
    val privacyPolicyText = "Privacy Policy"

    val annotatedString = buildAnnotatedString {
        append(initialText)

        // Terms of Use link
        val termsStart = length
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.tertiary,
            textDecoration = TextDecoration.Underline)) {
            append(termsOfUseText)
        }
        addStringAnnotation(
            tag = "TERMS", annotation = termsOfUseText, start = termsStart, end = length
        )

        append(andText)

        // Privacy Policy link
        val privacyStart = length
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.tertiary,
            textDecoration = TextDecoration.Underline)) {
            append(privacyPolicyText)
        }
        addStringAnnotation(
            tag = "PRIVACY", annotation = privacyPolicyText, start = privacyStart, end = length
        )
    }

    var textLayoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }

    Text(
        text = annotatedString,
        style = MaterialTheme.typography.labelSmall,
        modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    textLayoutResult?.let { layoutResult ->
                        val position = layoutResult.getOffsetForPosition(offset)
                        val annotations = annotatedString.getStringAnnotations(
                            start = position, end = position
                        )

                        annotations.firstOrNull()?.let { annotation ->
                            Log.d("ClickableText", "${annotation.tag} clicked!") // Debug log
                            onTextSelected(annotation.tag)
                        }
                    }
                }
            },
        onTextLayout = { layoutResult -> textLayoutResult = layoutResult },
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurface,
    )
}

@Composable
fun ButtonComponent(value: String, isEnabled: Boolean, onClick: () -> Unit){
    Button(
        onClick = { onClick() },
        enabled = isEnabled,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(MaterialTheme.colorScheme.secondary,
                MaterialTheme.colorScheme.primary)),
                shape = RoundedCornerShape(50.dp)
            ),

            contentAlignment = Alignment.Center

        ){
            Text(text = value,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ClickableLoginTextComponent(tryingToLogin: Boolean = true,
                                onTextSelected: (String) -> Unit)
{
    val initialText = if (tryingToLogin ) "Already have an account?  " else
        "Don't have an account yet? "
    val loginText = if (tryingToLogin) "Login" else "Register"


    val annotatedString = buildAnnotatedString {
        append(initialText)


        val loginStart = length
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.tertiary,
            textDecoration = TextDecoration.Underline)) {
            append(loginText)
        }
        addStringAnnotation(
            tag = "LOGIN", annotation = loginText, start = loginStart, end = length
        )

    }

    var textLayoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }

    Text(
        text = annotatedString,
        style = MaterialTheme.typography.labelLarge,
        modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    textLayoutResult?.let { layoutResult ->
                        val position = layoutResult.getOffsetForPosition(offset)
                        val annotations = annotatedString.getStringAnnotations(
                            start = position, end = position
                        )

                        annotations.firstOrNull()?.let { annotation ->
                            Log.d("ClickableText", "${annotation.tag} clicked!") // Debug log
                            onTextSelected(annotation.tag)
                        }
                    }
                }
            },
        onTextLayout = { layoutResult -> textLayoutResult = layoutResult },
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurface,
        textAlign = TextAlign.Center
    )
}

@Composable
fun UnderlineTextComponent(value:String){
    Text(text = value,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.tertiary,
        textDecoration = TextDecoration.Underline,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Right
    )

}

