package ru.netology.lesson6.attachment

/**
 * Абстрактный класс предок для всех видов
 * присоединенных файлов
 * в классе есть только тип, содержимое зависит от
 * этого типа
 */

sealed class Attachment{
    abstract val type : String
}
/**
 * Аудиозапись в прикрепленная к посту
 */
data class AttachmentAudio(
    override val type  : String = "audio",
             val audio : AttachmentAudioContent = AttachmentAudioContent()
) : Attachment()

/**
 * Объект, описывающий документ
 */
data class AttachmentDocument(
    override val type : String = "doc",
             val doc  : AttachmentDocumentContent = AttachmentDocumentContent()
) : Attachment()
/**
 * Это устаревший тип вложения. Он может быть возвращен лишь для записей,
 * созданных раньше 2013 года. Для более новых записей граффити возвращается в виде вложения с типом photo.
 */
data class AttachmentGraffiti (
    override val type     : String = "graffiti",
             val graffiti : AttachmentGraffitiContent = AttachmentGraffitiContent(),     //	идентификатор граффити.
) : Attachment()
/**
 * Заметка прикрепленная к посту
 */
data class AttachmentNote(
    override val type : String = "note",
             val note : AttachmentNoteContent = AttachmentNoteContent()
) : Attachment()

/**
 * Объект, описывающий фотографию (полученную по ссылке)
 */
data class AttachmentPhoto(
    override val type  : String = "photo",
             val photo : AttachmentPhotoContent = AttachmentPhotoContent()
) : Attachment()




