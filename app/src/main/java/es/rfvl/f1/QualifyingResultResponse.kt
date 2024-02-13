package es.rfvl.f1

import com.google.gson.annotations.SerializedName

data class QualifyingResultResponse(
    @SerializedName("number") val number: String,
    @SerializedName("position") val position: String,
    @SerializedName("Driver") val driver: DriverResponse,
    @SerializedName("Constructor") val constructor: ConstructorResponse,
    @SerializedName("Q1") val q1: String
)
