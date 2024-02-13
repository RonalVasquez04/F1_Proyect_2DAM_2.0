package es.rfvl.f1

import com.google.gson.annotations.SerializedName


data class DriverResponse(
    @SerializedName("givenName") val givenName: String,
    @SerializedName("familyName") val familyName: String
)
