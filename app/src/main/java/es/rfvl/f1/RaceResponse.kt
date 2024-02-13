package es.rfvl.f1

import com.google.gson.annotations.SerializedName

data class RaceResponse(
    @SerializedName("raceName") val raceName: String,
    @SerializedName("date") val date: String,
    @SerializedName("QualifyingResults") val qualifyingResults: List<QualifyingResultResponse>
)
