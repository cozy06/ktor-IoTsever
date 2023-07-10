package cozy06.com.data.receive

import kotlinx.serialization.Serializable

@Serializable
data class Power (
    val Name: String,
    val Power: String
)