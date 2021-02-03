package ru.netology.lesson6

import ru.netology.lesson6.Post
import java.util.concurrent.atomic.AtomicInteger

/**
 * Description WallService
 * Сервис для хранения и добавления постов
 */

class WallService() {
    //массив постов
    private var posts = emptyArray<Post>()
    //генератор уникальных Id
    private var idGenerator = AtomicInteger()
    private fun generateId() : Int  = idGenerator.incrementAndGet()
    /**
     *  функция возвращает количество элементов
     */
    fun count() = posts.size
    /**
     *  функция ищет элемент в массиве или возвращает null
     */
    fun find(predicate: (Post) -> Boolean): Post? = posts.firstOrNull(predicate)

    /**
     * Description функция add добавляет новый пост в список с уникальным id
     * возвращает пост с назначенным id
     */
    fun add(post : Post) = post.copy(id = generateId()).apply {
                                posts += this
                                return this
                            }
    /**
     * Description функция update обновляет содержимое поста (по сути редактирование)
     */
    fun update(post: Post): Boolean
    {
        val (match, rest) = posts.partition { it.id == post.id }
        if (match.isNotEmpty())
        {
            posts = rest.plus(post.copy(id = match.first().id, date = match.first().date)).toTypedArray()
        }
        return match.isNotEmpty()
    }
}