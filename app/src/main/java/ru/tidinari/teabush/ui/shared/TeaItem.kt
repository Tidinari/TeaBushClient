package ru.tidinari.teabush.ui.shared

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.tidinari.teabush.data.model.Tag
import ru.tidinari.teabush.data.model.Tea
import ru.tidinari.teabush.data.model.User
import java.util.Locale

/**
 * UI элемент, отображение чая
 *
 * @param modifier Jetpack Compose Modifier для модификации UI
 * @param tea чай для отображения
 * @param onClick что произойдёт при клике по элементу
 * @receiver
 */
@Composable
fun TeaCard(modifier: Modifier = Modifier, tea: Tea, onClick: () -> Unit) {
    Surface(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable { onClick() },
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(4.dp)
        ) {
            tea.image?.let { img ->
                Image(
                    bitmap = BitmapFactory.decodeByteArray(img, 0, img.size).asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Column(Modifier.weight(1f)) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = tea.name,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(start = 2.dp)
                    )
                }
                Text(
                    text = tea.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Icon(Icons.Default.Person, contentDescription = "Автор",
                        Modifier
                            .size(16.dp)
                            .padding(end = 4.dp))
                    Text(
                        text = tea.author.name,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                TagItems(tea.tags)
            }
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 4.dp)
            ) {
                Text(
                    text = "${tea.averageRating}",
                    style = MaterialTheme.typography.labelSmall,
                )
                Icon(Icons.Default.Star, contentDescription = null, Modifier.size(16.dp))
            }
        }
    }
}

/**
 * Отображает списог тэгов
 *
 * @param tags список тэгов для отображения
 */
@Composable
fun TagItems(tags: Array<Tag>) {
    val divider = "  •  "
    val text = buildAnnotatedString {
        val tagStyle = MaterialTheme.typography.bodySmall.toSpanStyle().copy(
            background = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
        )
        tags.forEachIndexed { index, tag ->
            if (index != 0) {
                append(divider)
            }
            withStyle(tagStyle) {
                append(" ${tag.name.uppercase(Locale.getDefault())} ")
            }
        }
    }
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(top = 4.dp)
    )
}

@Preview
@Composable
fun TeaCardPreview() {
    TeaCard(tea = Tea(
        1,
        "Название",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam semper elit quis diam sagittis tristique. Integer non lectus ac orci rhoncus egestas. Praesent dolor arcu, bibendum a rhoncus eget, tincidunt et dui. Vivamus ac turpis bibendum, varius sapien vitae, facilisis nunc. Etiam vulputate est ac orci accumsan, in varius tortor blandit. Proin ornare lectus vel rhoncus consequat. Curabitur interdum, justo eu blandit rhoncus, tortor erat malesuada lorem, sit amet ullamcorper augue felis eu nisi. Phasellus lorem nisl, rutrum non placerat quis, auctor eu sapien. Nullam tempor nisi nec mauris vulputate, vel tempor elit aliquet. Nulla laoreet urna quis ante vehicula tristique. Phasellus bibendum libero non tellus viverra cursus.",
        "Рецепт",
        null,
        User("Автор"),
        arrayOf(Tag(1, "Травяной"), Tag(2, "Вкусный")),
        false,
        3.5f
    ), onClick = {})
}