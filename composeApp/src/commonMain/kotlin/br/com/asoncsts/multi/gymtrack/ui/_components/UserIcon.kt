package br.com.asoncsts.multi.gymtrack.ui._components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.ui._theme.colors
import br.com.asoncsts.multi.gymtrack.ui._theme.typography
import coil3.compose.SubcomposeAsyncImage

@Composable
fun UserIcon(
    size: Dp,
    userName: String?,
    userPhotoUrl: String?,
    modifier: Modifier = Modifier
) {
    val borderSize = size / 12

    Row(
        modifier,
        horizontalArrangement = Arrangement
            .spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (userName != null) {
            Text(
                userName,
                style = typography().titleSmall
            )
        }

        SubcomposeAsyncImage(
            userPhotoUrl,
            userName,
            Modifier
                .size(size)
                .padding(4.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            error = {
                Icon(
                    Icons.Sharp.Person,
                    userName,
                    Modifier
                        .size(size)
                        .border(
                            borderSize,
                            colors().onBackground,
                            CircleShape
                        )
                        .padding(borderSize),
                    tint = colors().onBackground
                )
            },
            loading = {
                Loading(
                    size = size
                )
            }
        )
    }
}
