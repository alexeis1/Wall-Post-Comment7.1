package ru.netology.lesson6.attachment

/**
 * Содержимое прикрепления
 */
data class AttachmentDocumentContent (
    val id      : Int = 0 	 , // идентификатор документа.
    val ownerId : Int = 0 	 , // идентификатор пользователя, загрузившего документ.
    val title   : String = "", // название документа.
    val size    : Int = 0 	 , // размер документа в байтах.
    val ext     : String = "", // расширение документа.
    val url     : String = "", // адрес документа, по которому его можно загрузить.
    val date    : Int = 0 	 , // дата добавления в формате Unix time.
    val type    : Int = DocType.TXT.value, // тип документа. Возможные значения:DocType
    val preview : Preview? = null
)

/**
 * Document Preview
 */
data class Preview (
    val photo         : PhotoPreview?    = null,
    val graffiti      : GraffitiPreview? = null,
    val audio_message : AudioPreview?    = null
)

/**
 *  Preview Preview
 */
data class AudioPreview (
    val duration : Int = 0, // длительность аудиосообщения в секундах;
    val waveform : Array<Int> = emptyArray<Int>() , // массив значений (integer) для визуального отображения звука;
    val link_ogg : String = ""  , // URL .ogg-файла;
    val link_mp3 : String = ""  , // URL .mp3-файла.
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AudioPreview

        if (duration != other.duration) return false
        if (!waveform.contentEquals(other.waveform)) return false
        if (link_ogg != other.link_ogg) return false
        if (link_mp3 != other.link_mp3) return false

        return true
    }

    override fun hashCode(): Int {
        var result = duration
        result = 31 * result + waveform.contentHashCode()
        result = 31 * result + link_ogg.hashCode()
        result = 31 * result + link_mp3.hashCode()
        return result
    }
}


/**
 *  Preview Graffiti
 */
data class GraffitiPreview (
    val src    : String = "",// — URL документа с граффити;
    val width  : Int = 0,    // — ширина изображения в px;
    val height : Int = 0,    // — высота изображения в px.
)
/**
 *  Preview Photo
 */
data class PhotoPreview(
    val sizes : PhotoSize = PhotoSize()
)
/**
 * тип документа
 */
enum class DocType(val value : Int, val descr : String) {
    TXT     (1, "текстовые документы"),
    ARCH    (2, "архивы"),
    GIF     (3, "gif"),
    IMG     (4, "изображения"),
    AUDIO   (5, "аудио"),
    VIDEO   (6, "видео"),
    EBOOK   (7, "электронные книги"),
    UNKNOWN (8, "неизвестно")
}
