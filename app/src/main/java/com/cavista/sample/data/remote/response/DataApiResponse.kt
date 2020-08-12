package com.cavista.sample.data.remote.response

import com.cavista.sample.domain.model.UISearchData

/**
 * this the data model for parsing remote data
 */
data class DataApiResponse(val data: List<RemoteDataResponse>) {
    fun mapToDomainModel(): List<UISearchData> {
        var mutableList = mutableListOf<UISearchData>()
        data.forEach {
            it.images?.forEach { imageItem ->
                mutableList.add(UISearchData(it.id, it.title, imageItem.link))
            }
        }
        return mutableList
    }
}


/**
 * this the data model for parsing remote data
 */
data class RemoteDataResponse(val id: String, val title: String, val images: List<ImageList>)

data class ImageList(val link: String)


