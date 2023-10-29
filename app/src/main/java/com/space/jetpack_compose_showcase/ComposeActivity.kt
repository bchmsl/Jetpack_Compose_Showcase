package com.space.jetpack_compose_showcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * In compose activity, `setContent` method is called instead of `setContentView` in classic AppCompatActivity.
 */
class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProfilePage()
        }
    }
}

/**
 * This is [Composable] function for the whole profile page.
 * It consists of a single Column with another composable components: [ProfileHeader], [Spacer]s, [ProfileTextFields] and [ProfileButtons].
 * It has its own modifier, that is customized according to requirements.
 */
@Composable
fun ProfilePage() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ProfileHeader()
        Spacer(modifier = Modifier.height(16.dp))
        ProfileTextFields()
        Spacer(modifier = Modifier.height(16.dp))
        ProfileButtons()
    }
}

/**
 * This is [Composable] function for the profile header.
 * It consists of a single column with Text (Profile Page) and Image (img_profile_picture)
 * It has its own modifier for the element to fill max width of the screen and align it horizontally in the center.
 */
@Composable
fun ProfileHeader() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Profile Page",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(16.dp)
        )

        // Profile Picture
        Image(
            painter = painterResource(id = R.drawable.img_profile_picture),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .then(Modifier.align(Alignment.CenterHorizontally)), // Align the image to the center
            contentScale = ContentScale.FillBounds
        )
    }
}

/**
 * This is [Composable] function for all of the text fields in [ProfilePage].
 * It consists of a single column with multiple [ProfileTextField]s.
 */
@Composable
fun ProfileTextFields() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        ProfileTextField("Full Name", "John Doe")
        ProfileTextField("Email", "johndoe@email.com")
        ProfileTextField("Age", "30")
    }
}

/**
 * This is [Composable] function for all of the buttons in [ProfilePage]. It consists of two [ProfileButton]s.
 */
@Composable
fun ProfileButtons() {
    ProfileButton("Change Password")
    ProfileButton("Sign Out")
}

/**
 * This is [Composable] function for the text input fields [ProfileTextField].
 * It is a single text field with [Text] (e.g. Full Name) and [BasicTextField] with default value in it (e.g. John Doe).
 * It has [remember] function  called in it.
 * * The **`remember`** function is a fundamental part of Jetpack Compose that allows you to create and retain a value or state
 * across recompositions of a Composable function. It's primarily used for preserving state or data that should persist
 * as the UI is updated or redrawn.
 * * mutableStateOf is a function provided by Jetpack Compose that is used to create mutable state within a Composable.
 * It's one of the fundamental building blocks for managing and updating state in Compose applications.
 *
 */
@Composable
fun ProfileTextField(title: String, defaultText: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val (value, onValueChange) = remember { mutableStateOf(defaultText) } // Mutable state
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = LocalContentColor.current,
            modifier = Modifier.width(120.dp) // Adjust the width of the title
        )
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    // Handle Done action
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent) // Set a transparent background
                .padding(8.dp),
            textStyle = LocalTextStyle.current.copy(color = LocalContentColor.current)
        )
    }

    // Divider line
    Divider(
        color = Color.LightGray,
        thickness = 1.dp,
        modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
    )
}

/**
 *  This is [Composable] function for the buttons [ProfileButtons].
 *  It has **`onClick`** parameter with lambda property in it to easily handle button clicks.
 *  We don't need to call **`setOnClickListener`** anymore.
 */
@Composable
fun ProfileButton(text: String) {
    Button(
        onClick = {
            // Handle button click
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = text)
    }
}

/**
 * This is [Composable] function, which has additional [Preview] annotation with it.
 * It ensures that we can see realtime progress of building [Composable] screen without completely
 * building application on a virtual or physical device.
 * Every [Composable] function called in it will appear on preview pane in **Android Studio**.
 */
@Composable
@Preview
fun ProfilePagePreview() {
    ProfilePage()
}

