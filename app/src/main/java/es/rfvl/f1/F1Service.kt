package es.rfvl.f1

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
interface F1Service {

    @GET("{year}/{round}/qualifying.json")
    suspend fun getQualifyingResults(@Path("year") year: String, @Path("round") round: String): Response<DocumentResponse>
}