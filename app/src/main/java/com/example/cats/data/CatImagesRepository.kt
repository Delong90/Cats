package com.example.cats.data

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.cats.model.CatImageModel
import com.example.cats.repository.remote.CatsApiService
import com.example.cats.repository.remote.RemoteInjector



@ExperimentalPagingApi
class CatImagesRepository(
    val catsApiService: CatsApiService = RemoteInjector.injectCatsApiService(),
) {

    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 10


        fun getInstance() = CatImagesRepository()
    }



    fun letCatGoImagesLiveData(pagingConfig: PagingConfig = getDefaultPageConfig()): LiveData<PagingData<CatImageModel>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { CatGoImagePagingSource(catsApiService) }
        ).liveData
    }


    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true)
    }
}