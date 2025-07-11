package ru.gfastg98.avatarphysics.presentation.common

data class PhysicsConfig(
    val areaWidth: Float = 300f,
    val areaHeight: Float = 300f,
    val radiusStep: Float = 30f,
    val angleStepDeg: Float = 30f,
    val maxRadius: Float = 30f,
    val minRadius: Float = 15f,
    val amplitudeX: Float = 5f,
    val amplitudeY: Float = 5f,
    val periodXBase: Float = 1f,
    val periodYBase: Float = 1.5f,
    val periodIncrement: Float = 0.1f,
    val shadowOffsetX: Float = 4f,
    val shadowOffsetY: Float = 4f,
    val shadowRadiusExtra: Float = 4f,
    val shadowAlpha: Float = 0.2f,
    val repulsionCoefficient: Float = 0.1f
)