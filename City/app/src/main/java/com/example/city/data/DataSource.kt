package com.example.city.data

import android.R.attr.name

object DataSource {
    val categories = listOf(
        CityType(
            id = 1,
            name = com.example.city.R.string.category_shopping_name,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Shopping
        ),
        CityType(
            id = 2,
            name = com.example.city.R.string.category_Park_name,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Park
        ),
        CityType(
            id = 3,
            name = com.example.city.R.string.category_restaurant_name,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Restaurant
        )
    )
    val recommendations = listOf(
        Recommendation(
            id = 1,
            name = com.example.city.R.string.recommendation_1_name,
            description = com.example.city.R.string.recommendation_1_description,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Shopping
        ),
        Recommendation(
            id = 2,
            name = com.example.city.R.string.recommendation_2_name,
            description = com.example.city.R.string.recommendation_2_description,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Shopping
        ),
        Recommendation(
            id = 3,
            name = com.example.city.R.string.recommendation_3_name,
            description = com.example.city.R.string.recommendation_3_description,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Shopping
        ),
        Recommendation(
            id = 4,
            name = com.example.city.R.string.recommendation_4_name,
            description = com.example.city.R.string.recommendation_14_description,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Shopping
        ),
        Recommendation(
            id = 5,
            name = com.example.city.R.string.recommendation_5_name,
            description = com.example.city.R.string.recommendation_5_description,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Shopping
        ),
        Recommendation(
            id = 6,
            name = com.example.city.R.string.recommendation_6_name,
            description = com.example.city.R.string.recommendation_6_description,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Shopping
        ),
        Recommendation(
            id = 7,
            name = com.example.city.R.string.recommendation_7_name,
            description = com.example.city.R.string.recommendation_7_description,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Shopping
        ),
        Recommendation(
            id = 8,
            name = com.example.city.R.string.recommendation_8_name,
            description = com.example.city.R.string.recommendation_8_description,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Shopping
        ),
        Recommendation(
            id = 9,
            name = com.example.city.R.string.recommendation_9_name,
            description = com.example.city.R.string.recommendation_9_description,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Park
        ),
        Recommendation(
            id = 10,
            name = com.example.city.R.string.recommendation_10_name,
            description = com.example.city.R.string.recommendation_10_description,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Park
        ),
        Recommendation(
            id = 11,
            name = com.example.city.R.string.recommendation_11_name,
            description = com.example.city.R.string.recommendation_11_description,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Park
        ),
        Recommendation(
            id = 12,
            name = com.example.city.R.string.recommendation_13_name,
            description = com.example.city.R.string.recommendation_13_description,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Park
        ),
        Recommendation(
            id = 13,
            name = com.example.city.R.string.recommendation_14_name,
            description = com.example.city.R.string.recommendation_14_description,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Park
        ),
        Recommendation(
            id = 14,
            name = com.example.city.R.string.recommendation_12_name,
            description = com.example.city.R.string.recommendation_12_description,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Restaurant
        ),
        Recommendation(
            id = 15,
            name = com.example.city.R.string.recommendation_15_name,
            description = com.example.city.R.string.recommendation_15_description,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Restaurant
        ),
        Recommendation(
            id = 16,
            name = com.example.city.R.string.recommendation_16_name,
            description = com.example.city.R.string.recommendation_16_description,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Restaurant
        ),
        Recommendation(
            id = 17,
            name = com.example.city.R.string.recommendation_17_name,
            description = com.example.city.R.string.recommendation_17_description,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Restaurant
        ),
        Recommendation(
            id = 18,
            name = com.example.city.R.string.recommendation_18_name,
            description = com.example.city.R.string.recommendation_18_description,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Restaurant
        ),
        Recommendation(
            id = 19,
            name = com.example.city.R.string.recommendation_19_name,
            description = com.example.city.R.string.recommendation_19_description,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Restaurant
        ),
        Recommendation(
            id = 20,
            name = com.example.city.R.string.recommendation_20_name,
            description = com.example.city.R.string.recommendation_20_description,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Restaurant
        )


    )

    val recommendations_park = listOf(
        Recommendation(
            id = 9,
            name = com.example.city.R.string.recommendation_9_name,
            description = com.example.city.R.string.recommendation_9_description,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Park
        ),
        Recommendation(
            id = 10,
            name = com.example.city.R.string.recommendation_10_name,
            description = com.example.city.R.string.recommendation_10_description,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Park
        ),
        Recommendation(
            id = 11,
            name = com.example.city.R.string.recommendation_11_name,
            description = com.example.city.R.string.recommendation_11_description,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Park
        ),
        Recommendation(
            id = 12,
            name = com.example.city.R.string.recommendation_13_name,
            description = com.example.city.R.string.recommendation_13_description,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Park
        ),
        Recommendation(
            id = 13,
            name = com.example.city.R.string.recommendation_14_name,
            description = com.example.city.R.string.recommendation_14_description,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Park
        )
    )

    val recommendations_restaurant = listOf(
        Recommendation(
            id = 14,
            name = com.example.city.R.string.recommendation_12_name,
            description = com.example.city.R.string.recommendation_12_description,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Restaurant
        ),
        Recommendation(
            id = 15,
            name = com.example.city.R.string.recommendation_15_name,
            description = com.example.city.R.string.recommendation_15_description,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Restaurant
        ),
        Recommendation(
            id = 16,
            name = com.example.city.R.string.recommendation_16_name,
            description = com.example.city.R.string.recommendation_16_description,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Restaurant
        ),
        Recommendation(
            id = 17,
            name = com.example.city.R.string.recommendation_17_name,
            description = com.example.city.R.string.recommendation_17_description,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Restaurant
        ),
        Recommendation(
            id = 18,
            name = com.example.city.R.string.recommendation_18_name,
            description = com.example.city.R.string.recommendation_18_description,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Restaurant
        ),
        Recommendation(
            id = 19,
            name = com.example.city.R.string.recommendation_19_name,
            description = com.example.city.R.string.recommendation_19_description,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Restaurant
        ),
        Recommendation(
            id = 20,
            name = com.example.city.R.string.recommendation_20_name,
            description = com.example.city.R.string.recommendation_20_description,
            imageUrl = com.example.city.R.drawable.recommendation_image1,
            category = CategoryType.Restaurant
        ),

    )

    fun getPark(id: Long): Recommendation? {
        return recommendations_park.firstOrNull { it.id == id }
    }

    fun getShopping(id: Long): Recommendation? {
        return recommendations.firstOrNull { it.id == id }
    }

    fun getRestaurant(id: Long): Recommendation? {
        return recommendations_restaurant.firstOrNull { it.id == id }
    }

    val defaultRecommendation = Recommendation(
        id = 20,
        name = com.example.city.R.string.recommendation_20_name,
        description = com.example.city.R.string.recommendation_20_description,
        imageUrl = com.example.city.R.drawable.recommendation_image1,
        category = CategoryType.Restaurant
    )
}