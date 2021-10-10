package com.example.cats.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.cats.model.CatImageModel
import com.example.cats.repository.remote.CatsApiService
import com.example.cats.data.CatImagesRepository.Companion.DEFAULT_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException


class CatGoImagePagingSource(private val catsApiService: CatsApiService) :
    PagingSource<Int, CatImageModel>() {

    @ExperimentalPagingApi
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatImageModel> {

        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = catsApiService.getCatsImages(page, params.loadSize)
            LoadResult.Page(
                response, prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CatImageModel>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

}