package ru.netology.lesson6.attachment

/**
 * Абстрактный класс предок для всех видов
 * присоединенных файлов
 * в классе есть только тип, содержимое зависит от
 * этого типа
 */

import kotlinx.serialization.Serializable

@Serializable
sealed class Attachment(
    val type    : String = ""
)
/**
 * Аудиозапись в прикрепленная к посту
 */
@Serializable
data class AttachmentAudio(
    val audio : AttachmentAudioContent = AttachmentAudioContent()
) : Attachment()

/**
 * Объект, описывающий документ
 */
@Serializable
data class AttachmentDocument(
    val doc : AttachmentDocumentContent = AttachmentDocumentContent()
) : Attachment()
/**
 * Это устаревший тип вложения. Он может быть возвращен лишь для записей,
 * созданных раньше 2013 года. Для более новых записей граффити возвращается в виде вложения с типом photo.
 */
@Serializable
data class AttachmentGraffiti (
    val graffiti : AttachmentGraffitiContent = AttachmentGraffitiContent(),     //	идентификатор граффити.
)
/**
 * Заметка прикрепленная к посту
 */
@Serializable
data class AttachmentNote(
    val note : AttachmentNoteContent = AttachmentNoteContent()
) : Attachment()

/**
 * Объект, описывающий фотографию (полученную по ссылке)
 */
@Serializable
data class AttachmentPhoto(
    val photo : AttachmentPhotoContent = AttachmentPhotoContent()
) : Attachment()




