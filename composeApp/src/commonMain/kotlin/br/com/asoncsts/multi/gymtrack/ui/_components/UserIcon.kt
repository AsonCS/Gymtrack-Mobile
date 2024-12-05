package br.com.asoncsts.multi.gymtrack.ui._components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import coil3.compose.SubcomposeAsyncImage

@Composable
fun UserIcon(
    size: Dp,
    userName: String?,
    userPhotoUrl: String?,
    modifier: Modifier = Modifier
) {
    SubcomposeAsyncImage(
        userPhotoUrl,
        userName,
        modifier
            .size(size)
            .clip(CircleShape),
        contentScale = ContentScale.Crop,
        error = {
            Icon(
                Icons.Filled.Person,
                userName,
                Modifier
                    .size(size)
            )
        },
        loading = {
            Loading(
                size = size
            )
        }
    )
}
