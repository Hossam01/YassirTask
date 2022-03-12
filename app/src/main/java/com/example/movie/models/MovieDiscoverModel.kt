package com.example.movie.models

import java.io.Serializable

data class MovieDiscoverModel(
	val page: Int? = null,
	val totalPages: Int? = null,
	val results: List<ResultsItem?>? = null,
	val totalResults: Int? = null
)

data class ResultsItem(
	val overview: String? = null,
	val originalLanguage: String? = null,
	val originalTitle: String? = null,
	val video: Boolean? = null,
	val title: String? = null,
	val genreIds: List<Int?>? = null,
	val poster_path: String? = null,
	val backdrop_path: String? = null,
	val release_date: String? = null,
	val popularity: Double? = null,
	val vote_average: Double? = null,
	val id: Int? = null,
	val adult: Boolean? = null,
	val vote_count: Int? = null
):Serializable

