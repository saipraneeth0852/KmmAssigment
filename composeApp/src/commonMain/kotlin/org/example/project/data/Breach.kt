package org.example.project.data

import kotlinx.serialization.Serializable

@Serializable
data class Breach(
    val Name: String,
    val Title: String,
    val Domain: String,
    val BreachDate: String,
    val AddedDate: String,
    val ModifiedDate: String,
    val PwnCount: Int,
    val Description: String,
    val LogoPath: String,
    val DataClasses: List<String>,
    val IsVerified: Boolean,
    val IsFabricated: Boolean,
    val IsSensitive: Boolean,
    val IsRetired: Boolean,
    val IsSpamList: Boolean,
    val IsMalware: Boolean,
    val IsSubscriptionFree: Boolean
)