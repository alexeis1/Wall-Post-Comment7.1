package ru.netology.lesson7

import org.junit.Assert.*
import org.junit.Test

class WallServiceTest {

    @Test
    fun update_IdFound() {
        val service = WallService()
        val postWithId = service.add(Post(id = 0, text = "text 1"))
        service.update(postWithId).apply {
            assertTrue(this)
        }
    }

    @Test
    fun update_IdNotFound() {
        val service = WallService()
        val post1   = Post(id = 0, text = "text 1")
        service.add(post1)
        service.update(post1).apply {
            assertFalse(this)
        }
    }

    @Test
    fun add_checkIdNotZero() {
        val service    = WallService()
        val postWithId = service.add(Post(id = 0, text = "text 1"))
        assertNotEquals(0, postWithId)
    }

    @Test
    fun add_checkAdded() {
        val service = WallService()
        service.add(Post(id = 0, text = "text 1"))
        assertNotEquals(0, service.count())
    }
    /**
     * Description тест проверяет что функция update обновила содержимое поля текст
     */
    @Test
    fun update_checkText() {
        val service  = WallService()
        //добавляем 2 поста, чтобы было среди чего искать
        val post1    = Post(id = 0, text = "text 1")
        val post2    = Post()
        val newPost1 = service.add(post1).copy(text = "new text1")
                       service.add(post2)
        //меняем текст в 1м посте
        service.update(newPost1)
        //находим этот пост и проверяем го содержимое
        service.find { it.id == newPost1.id } .apply {
            assertNotNull(this)
            assertEquals(this!!.text, "new text1")
        }
    }

    @Test(expected = PostNotFoundException::class)
    fun createComment_postIdNotFound() {
        val service  = WallService()
        val post1    = Post(id = 0, text = "text 1")
        val newPost1 = service.add(post1)
        val comment  = Comment(newPost1.id+1)
        service.createComment(comment)
    }

    @Test
    fun createComment_postIdFound(){
        val service  = WallService()
        val post1    = Post(id = 0, text = "text 1")
        val newPost1 = service.add(post1)
        val comment  = Comment(newPost1.id)
        service.createComment(comment)
    }

    @Test(expected = CommentNotFoundException::class)
    fun reportComment_commentNotFoundById() {
        val service  = WallService()
        val post1    = Post(id = 0, text = "text 1")
        val newPost1 = service.add(post1)
        val comment  = Comment(postId = newPost1.id, id = 1)
        service.createComment(comment)
        service.reportComment(comment.fromId, comment.id+1, ReasonDesc.ABUSE)
    }

    @Test(expected = CommentNotFoundException::class)
    fun reportComment_commentNotFoundByFormId() {
        val service  = WallService()
        val post1    = Post(id = 0, text = "text 1")
        val newPost1 = service.add(post1)
        val comment  = Comment(postId = newPost1.id, id = 1)
        service.createComment(comment)
        service.reportComment(comment.fromId+1, comment.id, ReasonDesc.ABUSE)
    }

    @Test
    fun reportComment_success() {
        val service  = WallService()
        val post1    = Post(id = 0, text = "text 1")
        val newPost1 = service.add(post1)
        val comment  = Comment(postId = newPost1.id, id = 1)
        service.createComment(comment)
        service.reportComment(comment.fromId, comment.id, ReasonDesc.ABUSE)
    }
}