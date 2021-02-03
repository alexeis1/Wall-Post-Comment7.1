package ru.netology.lesson7

import ru.netology.lesson7.attachment.Attachment
import java.util.*

/**
 * Объект, описывающий комментарий к записи
 */
data class Comment (
    val postId         : Int,
    val id             : Int = 0,         //идентификатор комментария.
    val fromId         : Int = 0,     //идентификатор автора комментария.
    val date           : Int = 0,     //дата создания комментария в формате Unixtime.
    val text           : String = "", //текст комментария
    val donut          : CommentDonut = CommentDonut(), //информация о VK Donut.
    val replyToUser    : Int = 0,   //идентификатор пользователя или сообщества, в ответ которому
                                    // оставлен текущий комментарий (если применимо).
    val replyToComment : Int = 0,  //идентификатор комментария, в ответ на который оставлен текущий (если применимо)
    val attachments    : Array<Attachment> = emptyArray<Attachment>(), //медиавложения комментария (фотографии, ссылки и т.п.).
    val parentsStack   : Stack<Int> = Stack<Int>(),  //массив идентификаторов родительских комментариев.
    val thread         : CommentThread = CommentThread()
)
{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Comment

        if (id != other.id) return false
        if (fromId != other.fromId) return false
        if (date != other.date) return false
        if (text != other.text) return false
        if (donut != other.donut) return false
        if (replyToUser != other.replyToUser) return false
        if (replyToComment != other.replyToComment) return false
        if (!attachments.contentEquals(other.attachments)) return false
        if (parentsStack != other.parentsStack) return false
        if (thread != other.thread) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + fromId
        result = 31 * result + date
        result = 31 * result + text.hashCode()
        result = 31 * result + donut.hashCode()
        result = 31 * result + replyToUser
        result = 31 * result + replyToComment
        result = 31 * result + attachments.contentHashCode()
        result = 31 * result + parentsStack.hashCode()
        result = 31 * result + thread.hashCode()
        return result
    }

}

/**
 * Информация о вложенной ветке комментариев,
 */
data class CommentThread(
    val count           : Int = 0,     //— количество комментариев в ветке.
    val items           : Array<Comment>? = null, //— массив объектов комментариев к записи (только для метода wall.getComments).
    val canPost         : Boolean = false, //– может ли текущий пользователь оставлять комментарии в этой ветке.
    val showReplyButton : Boolean = false, //– нужно ли отображать кнопку «ответить» в ветке.
    val groupsCanPost   : Boolean = false //– могут ли сообщества оставлять комментарии в ветке.
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CommentThread

        if (count != other.count) return false
        if (items != null) {
            if (other.items == null) return false
            if (!items.contentEquals(other.items)) return false
        } else if (other.items != null) return false
        if (canPost != other.canPost) return false
        if (showReplyButton != other.showReplyButton) return false
        if (groupsCanPost != other.groupsCanPost) return false

        return true
    }

    override fun hashCode(): Int {
        var result = count
        result = 31 * result + (items?.contentHashCode() ?: 0)
        result = 31 * result + canPost.hashCode()
        result = 31 * result + showReplyButton.hashCode()
        result = 31 * result + groupsCanPost.hashCode()
        return result
    }
}

/**
 * информация о VK Donut
 */
data class CommentDonut(
    val isDon : Boolean = false, //  является ли комментатор подписчиком VK Donut.
    val placeholder : String = ""// заглушка для пользователей, которые не оформили подписку VK Donut.
)
