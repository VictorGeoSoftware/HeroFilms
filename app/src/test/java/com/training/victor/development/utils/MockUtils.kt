package com.training.victor.development.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.training.victor.development.data.models.MovieItem
import com.training.victor.development.network.responses.ThorFilmsResp

fun getMockedThorMoviesResp(): ThorFilmsResp {
    val respString = "{\n" +
            "  \"page\": 1,\n" +
            "  \"total_results\": 129,\n" +
            "  \"total_pages\": 7,\n" +
            "  \"results\": [\n" +
            "    {\n" +
            "      \"vote_count\": 11425,\n" +
            "      \"id\": 10195,\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 6.7,\n" +
            "      \"title\": \"Thor\",\n" +
            "      \"popularity\": 31.153,\n" +
            "      \"poster_path\": \"/cSBvRyle5z5lbWbbiQQwla0j5Fs.jpg\",\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"original_title\": \"Thor\",\n" +
            "      \"genre_ids\": [\n" +
            "        12,\n" +
            "        14,\n" +
            "        28\n" +
            "      ],\n" +
            "      \"backdrop_path\": \"/LvmmDZxkTDqp0DX7mUo621ahdX.jpg\",\n" +
            "      \"adult\": false,\n" +
            "      \"overview\": \"Against his father Odin's will, The Mighty Thor - a powerful but arrogant warrior god - recklessly reignites an ancient war. Thor is cast down to Earth and forced to live among humans as punishment. Once here, Thor learns what it takes to be a true hero when the most dangerous villain of his world sends the darkest forces of Asgard to invade Earth.\",\n" +
            "      \"release_date\": \"2011-04-21\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"vote_count\": 9910,\n" +
            "      \"id\": 284053,\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 7.5,\n" +
            "      \"title\": \"Thor: Ragnarok\",\n" +
            "      \"popularity\": 48.928,\n" +
            "      \"poster_path\": \"/rzRwTcFvttcN1ZpX2xv4j3tSdJu.jpg\",\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"original_title\": \"Thor: Ragnarok\",\n" +
            "      \"genre_ids\": [\n" +
            "        28,\n" +
            "        12,\n" +
            "        35,\n" +
            "        14,\n" +
            "        878\n" +
            "      ],\n" +
            "      \"backdrop_path\": \"/r9DmhMfsndC8QtrXKyad3KCRdcC.jpg\",\n" +
            "      \"adult\": false,\n" +
            "      \"overview\": \"Thor is on the other side of the universe and finds himself in a race against time to get back to Asgard to stop Ragnarok, the prophecy of destruction to his homeworld and the end of Asgardian civilization, at the hands of an all-powerful new threat, the ruthless Hela.\",\n" +
            "      \"release_date\": \"2017-10-25\"\n" +
            "    }]\n" +
            "}"

    return Gson().fromJson(respString, ThorFilmsResp::class.java)
}

fun getMockedMoviesList(): ArrayList<MovieItem> {

    val moviesListString = "    [\n" +
            "    {\n" +
            "      \"vote_count\": 11425,\n" +
            "      \"id\": 10195,\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 6.7,\n" +
            "      \"title\": \"Thor\",\n" +
            "      \"popularity\": 31.153,\n" +
            "      \"poster_path\": \"/cSBvRyle5z5lbWbbiQQwla0j5Fs.jpg\",\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"original_title\": \"Thor\",\n" +
            "      \"genre_ids\": [\n" +
            "        12,\n" +
            "        14,\n" +
            "        28\n" +
            "      ],\n" +
            "      \"backdrop_path\": \"/LvmmDZxkTDqp0DX7mUo621ahdX.jpg\",\n" +
            "      \"adult\": false,\n" +
            "      \"overview\": \"Against his father Odin's will, The Mighty Thor - a powerful but arrogant warrior god - recklessly reignites an ancient war. Thor is cast down to Earth and forced to live among humans as punishment. Once here, Thor learns what it takes to be a true hero when the most dangerous villain of his world sends the darkest forces of Asgard to invade Earth.\",\n" +
            "      \"release_date\": \"2011-04-21\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"vote_count\": 9910,\n" +
            "      \"id\": 284053,\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 7.5,\n" +
            "      \"title\": \"Thor: Ragnarok\",\n" +
            "      \"popularity\": 48.928,\n" +
            "      \"poster_path\": \"/rzRwTcFvttcN1ZpX2xv4j3tSdJu.jpg\",\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"original_title\": \"Thor: Ragnarok\",\n" +
            "      \"genre_ids\": [\n" +
            "        28,\n" +
            "        12,\n" +
            "        35,\n" +
            "        14,\n" +
            "        878\n" +
            "      ],\n" +
            "      \"backdrop_path\": \"/r9DmhMfsndC8QtrXKyad3KCRdcC.jpg\",\n" +
            "      \"adult\": false,\n" +
            "      \"overview\": \"Thor is on the other side of the universe and finds himself in a race against time to get back to Asgard to stop Ragnarok, the prophecy of destruction to his homeworld and the end of Asgardian civilization, at the hands of an all-powerful new threat, the ruthless Hela.\",\n" +
            "      \"release_date\": \"2017-10-25\"\n" +
            "    }\n" +
            "    ]"

    val listType = object : TypeToken<List<MovieItem>>(){}.type
    return Gson().fromJson(moviesListString, listType)
}