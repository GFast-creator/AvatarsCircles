package ru.gfastg98.avatarphysics.presentation.common

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random

@Composable
fun AvatarView(
    modifier: Modifier = Modifier,
    users: List<User>,
    config: PhysicsConfig = PhysicsConfig()
) {
    Canvas(
        modifier = modifier
    ) {
        val circles = calcCords(size)

        circles.forEach { circle ->
            drawCircle(
                color = Color.hsl(
                    hue = Random.nextFloat() * 360,
                    saturation = 0.7f,
                    lightness = 0.5f
                ),
                radius = circle.radius,
                center = Offset(circle.pX, circle.pY),
                style = androidx.compose.ui.graphics.drawscope.Fill
            )
            drawCircle(
                color = Color.Black,
                radius = circle.radius,
                center = Offset(circle.pX, circle.pY),
                style = androidx.compose.ui.graphics.drawscope.Stroke(1f)
            )
        }

        drawLine(Color.Black, size.center.copy(y = 0f), size.center.copy(y = size.height))
        drawLine(Color.Black, size.center.copy(x = 0f), size.center.copy(x = size.width))
    }
}

fun isValidCircle(
    x: Float,
    y: Float,
    radius: Float,
    circles: List<AvatarP>,
    canvasWidth: Float,
    canvasHeight: Float,
    margin: Float = 10f
): Boolean {
    if (x - radius < margin || x + radius > canvasWidth - margin ||
        y - radius < margin || y + radius > canvasHeight - margin
    ) {
        return false
    }
    for (circle in circles) {
        val dx = x - circle.pX
        val dy = y - circle.pY
        val distance = sqrt(dx * dx + dy * dy)
        if (distance < radius + circle.radius + 5f) {
            return false // Пересечение
        }
    }
    return true
}

const val baseRadius = 30f // Базовый радиус для остальных кругов
// Первый круг в центре
val centerRadius = (baseRadius + 10f) * 1.3f

const val spiralStep = 10f // Шаг спирали (расстояние между витками)
const val maxAttemptsPerCircle = 10000

fun calcCords(size: Size): List<AvatarP> {
    val circles = mutableListOf<AvatarP>()

    for (i in 0 .. 9) {
        var placed = false
        val radius = if (i == 0) centerRadius else baseRadius + Random.nextFloat() * 10f
        var attempts = 0

        var baseAngle = PI.toFloat() * Random.nextFloat() // Равномерное деление углов
        var angle = baseAngle // Равномерное деление углов
        var distance = spiralStep + radius + 20f // Увеличиваем расстояние для каждого витка

        while (!placed && attempts < maxAttemptsPerCircle) {
            // 10 g
            if (angle.toDouble() >= baseAngle + 2 * PI) {
                angle = baseAngle
                distance += spiralStep
            }

            val x = size.center.x + distance * cos(angle)
            val y = size.center.y + distance * sin(angle) * 0.7f

            if (isValidCircle(x, y, radius, circles, size.width, size.height)) {
                circles.add(AvatarP(i + 1, x, y, radius))
                placed = true
            }

            angle += (1 / 50f * 2 * PI).toFloat()

            attempts++
        }
    }

    return circles
}

fun List<AvatarP>.normalize(size : Size) : List<AvatarP> {
    var sorted = this.sortedBy { it.pX }
    val left = sorted.first().pX - sorted.first().radius
    val right = sorted.last().pX + sorted.last().radius
    var center = (right - left) / 2f
    var delta = size.center.x - center

    sorted = sorted.map { it.copy(pX = it.pX - delta) }
        .sortedBy { it.pY }

    val top = sorted.first().pY - sorted.first().radius
    val bottom = sorted.last().pY + sorted.last().radius
    center = (top - bottom) / 2f
    delta = size.center.y - center

    return sorted.map { it.copy(pY = it.pY - delta) }
}