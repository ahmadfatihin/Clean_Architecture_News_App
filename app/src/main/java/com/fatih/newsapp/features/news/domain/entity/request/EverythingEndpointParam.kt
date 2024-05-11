package com.fatih.newsapp.features.news.domain.entity.request

data class EverythingEndpointParam(
    val q: String? = null,
    val source: String? = null,
    val pageSize: Int? = null,
    var page: Int? = null,
    val searchIn: String? = null,
    val excludeDomains: String? = null,
    val from: String? = null,
    val to: String? = null,
    val language: String? = null,
    val sortBy: String? = null,
) {

    @Suppress("UNCHECKED_CAST")
    fun toHashMap() : HashMap<String, Any> {
        val query = hashMapOf(
            "q" to q,
            "language" to language,
            "source" to source,
            "pageSize" to pageSize,
            "page" to page,
            "searchIn" to searchIn,
            "excludeDomains" to excludeDomains,
            "from" to from,
            "to" to to,
            "sortBy" to sortBy,
        )


        return query.filterValues { it != null } as HashMap<String, Any>
    }

}
