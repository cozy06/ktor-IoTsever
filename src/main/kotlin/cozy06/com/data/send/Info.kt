package cozy06.com.data.send

import kotlinx.serialization.Serializable

@Serializable
data class Info (
    val Name: String,
    val Loc: String,
    val type: String
)