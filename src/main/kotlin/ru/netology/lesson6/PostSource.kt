package ru.netology.lesson6

/**
 * Description
 * класс  PostSource, описывающий способ размещения записи на стене
 */

data class PostSource(
    val type      : String = SourceType.VK.value, // Варианты из SourceType
    val platform  : String = Platform.ANDROID.value, // Варианты Platform
    val data      : String = ActionType.PROFILEACTIVITY.value, // Варианты из ActionType
    val url       : String = "", // URL ресурса, с которого была опубликована запись.
) {
    /**
     * Description SourceType
     * тип источника
     */
    enum class SourceType(val value : String){
        VK    ("vk"    ),// запись создана через основной интерфейс сайта (http://vk.com/);
        WIDGET("widget"),//— запись создана через виджет на стороннем сайте;
        API   ("api"   ),//— запись создана приложением через API;
        RSS   ("rss"   ),//— запись создана посредством импорта RSS-ленты со стороннего сайта;
        SMS   ("sms"   ),//— запись создана посредством отправки SMS-сообщения на специальный номер.;;
    }
    /**
     * Description Platform
     * название платформы
     */
    enum class Platform(val value : String){
        ANDROID("android"),
        IPHONE ("iphone"),
        WPHONE ("wphone")
    }

    /**
     * Description ActionType
     * тип действия (только для type = vk или widget). Возможные значения:
     */
    enum class ActionType(val value : String){
        PROFILEACTIVITY("profile_activity"), // — изменение статуса под именем пользователя (для type = vk);
        PROFILEPHOTO   ("profile_photo"   ), // — изменение профильной фотографии пользователя (для type = vk);
        COMMENTS        ("comments"        ), // — виджет комментариев (для type = widget);
        LIKE            ("like"            ), // — виджет «Мне нравится» (для type = widget);
        POLL            ("poll"            )  // — виджет опросов (для type = widget);
    }
}