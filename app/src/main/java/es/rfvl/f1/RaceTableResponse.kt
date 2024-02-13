package es.rfvl.f1

import com.google.gson.annotations.SerializedName

data class RaceTableResponse(
    @SerializedName("Races") val races: List<RaceResponse>
)
