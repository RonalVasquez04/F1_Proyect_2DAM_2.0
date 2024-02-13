package es.rfvl.f1

import com.google.gson.annotations.SerializedName

data class MRDataResponse(
    @SerializedName("RaceTable") val raceTable: RaceTableResponse
)
