package ru.netology.lesson7.attachment

/**
 * Объект, описывающий аудиозапись
 */
data class AttachmentAudioContent (
    val id       : Int = 0,     //	идентификатор аудиозаписи.
    val ownerId  : Int = 0,     //	идентификатор владельца аудиозаписи.
    val artist   : String = "", //	исполнитель.
    val title    : String = "", //	название композиции.
    val duration : Int = 0,     //	длительность аудиозаписи в секундах.
    val url      : String = "", //	ссылка на mp3.
    val lyricsId : Int = 0,     //	идентификатор текста аудиозаписи (если доступно).
    val albumId  : Int = 0,     //	идентификатор альбома, в котором находится аудиозапись (если присвоен).
    val genreId  : Int = 0,     //	идентификатор жанра из списка аудио жанров.
    val date     : Int = 0,     //	дата добавления.
    val noSearch : Boolean = false, //, 1, если включена опция «Не выводить при поиске». Если опция отключена, поле не возвращается.
    val isHq     : Boolean = false, //, 1, если аудио в высоком качестве.
)