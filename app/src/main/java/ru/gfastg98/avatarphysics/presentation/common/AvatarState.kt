package ru.gfastg98.avatarphysics.presentation.common

data class AvatarState(
    val baseX: Float,
    val baseY: Float,
    val radius: Float,
    val offsetX: Float,
    val offsetY: Float,
    val shadowX: Float,
    val shadowY: Float,
    val shadowRadius: Float
)

data class AvatarP(
    val id: Int,
    val pX: Float, val pY: Float,
    val radius: Float
)