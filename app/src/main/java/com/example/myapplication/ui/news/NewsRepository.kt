package com.example.myapplication.ui.news

import com.example.myapplication.api.ResultX
import com.example.myapplication.data.Article
import com.example.myapplication.data.ArticleDao
import retrofit2.Response
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val articleDao: ArticleDao,
    private val newsService: NewsService) {

    suspend fun getNews(): Response<List<ResultX>> {

        val numberOfItems = articleDao.getNumberOfItems()
        if (numberOfItems > 0) {
            return Response.success(articleDao.getArticles().map { it.toDomainModel() })
        } else {
            val newsJson = newsService.getNews()
            val news = newsJson.body()?.results?.map { it.toDatabaseModel() }
            if (news != null) {
                articleDao.insert(news)
            }
            if (news != null) {
                return Response.success(news.map { it.toDomainModel() })
            }
        }
        return Response.success(emptyList())
    }


    private fun ResultX.toDatabaseModel(): Article {
        return Article(
            id =  UUID.randomUUID().toString(),
            title = title,
            description = description ?: "",
            imageUrl  =  image_url ?: ""
        )
    }

    private fun Article.toDomainModel(): ResultX {
        return ResultX(
            id = id,
            title = title,
            description = description,
            image_url = imageUrl,
            category = emptyList(),
            content = "",
            country = emptyList(),
            creator = emptyList(),
            full_description = "",
            keywords = emptyList(),
            language = "",
            link =  "",
            pubDate = "",
            source_id = "",
            video_url = ""
        )
    }

    companion object {
        private const val LIMIT = 10
    }
}

