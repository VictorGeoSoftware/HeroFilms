package com.training.victor.development.network.responses.models

import com.google.gson.annotations.SerializedName

data class SpokenLanguagesItem(@SerializedName("name")  val name: String = "",
                               @SerializedName("iso_639_1")  val iso: String = "")