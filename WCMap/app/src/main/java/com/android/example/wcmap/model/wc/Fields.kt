package com.android.example.wcmap.model.wc

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

/*
The implementation of the Parcelable interface
allows to pass an object between the different Android components
like the activity and the fragment (wcList and wcDetails)

The @Parcelize annotation instructs the Kotlin compiler to automatically generate
the writeToParcel() and describeContents() methods, as well as the CREATOR factory class.
 */
@Parcelize
data class Fields(

    @Json(name = "typtoil") var typtoil: String,
    @Json(name = "wgs84_lalo") var wgs84Lalo: List<Double>,
    @Json(name = "pmr") var pmr: String,
    @Json(name = "heureouv") var heureouv: String,
    @Json(name = "typeeng") var typeeng: String,
    @Json(name = "gratuite") var gratuite: String,
    @Json(name = "rue") var rue: String,
    @Json(name = "specloc") var specloc: String,
    @Json(name = "nrpol") var nrpol: String,
    @Json(name = "wgs84_long") var wgs84Long: String,
    @Json(name = "adrvoisnl") var adrvoisnl: String,
    @Json(name = "z_pcdd_nl") var zPcddNl: String,
    @Json(name = "wgs84_lat") var wgs84Lat: String,
    @Json(name = "niveau") var niveau: Int,
    @Json(name = "z_pcdd_fr") var zPcddFr: String,
    @Json(name = "typedut") var typedut: String,
    @Json(name = "typefr") var typefr: String,
    @Json(name = "adrvoisfr") var adrvoisfr: String,
    @Json(name = "gestion") var gestion: String

) : Parcelable