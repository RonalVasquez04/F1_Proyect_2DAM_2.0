package es.rfvl.f1

import com.google.gson.annotations.SerializedName

data class DocumentResponse(
    @SerializedName("MRData") val mrData: MRDataResponse
)
