package ru.netology.lesson6.attachment

import kotlinx.serialization.Serializable

/**
 * Содержимое вложения graffiti
 */
@Serializable
data class AttachmentGraffitiContent (
    val id      : Int = 0,     //	идентификатор граффити.
    val ownerId : Int = 0,     //	идентификатор автора граффити.
    val photo130: String = "", //	URL изображения для предпросмотра.
    val photo604: String = "", //	URL полноразмерного изображения.
)