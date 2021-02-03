package ru.netology.lesson6.attachment

/**
 * Содержимое заметки прикрепленная к посту
 */
data class AttachmentNoteContent (
    val id           : Int = 0 	  , //  идентификатор заметки.
    val ownerId      : Int = 0 	  , // идентификатор владельца заметки.
    val title        : String = "", // заголовок заметки.
    val text         : String = "", //	текст заметки.
    val date         : Int = 0 	  , //  дата создания заметки в формате Unixtime.
    val comments     : Int = 0 	  , // количество комментариев.
    val readComments : Int = 0 	  , // количество прочитанных комментариев (только при запросе информации
                                    // о заметке текущего пользователя).
    val viewUrl      : String = "" //	URL страницы для отображения заметки.
)
