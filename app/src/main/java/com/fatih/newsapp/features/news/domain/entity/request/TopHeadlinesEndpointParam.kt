package com.pbi.newsapp.domain.model.request

data class TopHeadlinesEndpointParam(
    val q: String? = null,
    val source: String? = null,
    val pageSize: Int? = null,
    var page: Int? = null,
    val country: String? = null,
    val category: String? = null,
) {
    @Suppress("UNCHECKED_CAST")
    fun toHashMap() : HashMap<String, Any> {
        val param = hashMapOf(
            "q" to q,
            "source" to source,
            "pageSize" to pageSize,
            "page" to page,
            "country" to country,
            "category" to category
        )

        return param.filterValues { it != null } as HashMap<String, Any>
    }
}
