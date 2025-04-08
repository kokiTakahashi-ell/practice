/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.reply.data.local

import com.example.reply.R
import com.example.reply.data.Email
import com.example.reply.data.MailboxType

/**
 * A static data store of [Email]s.
 */

object LocalEmailsDataProvider {

    val allEmails = listOf(
//        Email(
//            id = 0L,
//            sender = LocalAccountsDataProvider.getContactAccountById(9L),
//            recipients = listOf(LocalAccountsDataProvider.defaultAccount),
//            subject = R.string.email_0_subject,
//            body = R.string.email_0_body,
//            createdAt = R.string.email_0_time,
//        ),
        Email(
            id = 1,
            name = com.example.reply.R.string.recommendation_1_name,
            description = com.example.reply.R.string.recommendation_1_description,
            imageUrl = com.example.reply.R.drawable.avatar_2,
            mailbox = MailboxType.Inbox
        ),
        Email(
            id = 2,
            name = com.example.reply.R.string.recommendation_2_name,
            description = com.example.reply.R.string.recommendation_2_description,
            imageUrl = com.example.reply.R.drawable.avatar_2,
            mailbox = MailboxType.Inbox
        ),
        Email(
            id = 3,
            name = com.example.reply.R.string.recommendation_3_name,
            description = com.example.reply.R.string.recommendation_3_description,
            imageUrl = com.example.reply.R.drawable.avatar_2,
            mailbox = MailboxType.Inbox
        ),
        Email(
            id = 4,
            name = com.example.reply.R.string.recommendation_4_name,
            description = com.example.reply.R.string.recommendation_14_description,
            imageUrl = com.example.reply.R.drawable.avatar_2,
            mailbox = MailboxType.Inbox
        ),
        Email(
            id = 5,
            name = com.example.reply.R.string.recommendation_5_name,
            description = com.example.reply.R.string.recommendation_5_description,
            imageUrl = com.example.reply.R.drawable.avatar_2,
            mailbox = MailboxType.Inbox
        ),
        Email(
            id = 6,
            name = com.example.reply.R.string.recommendation_6_name,
            description = com.example.reply.R.string.recommendation_6_description,
            imageUrl = com.example.reply.R.drawable.avatar_2,
            mailbox = MailboxType.Sent
        ),
        Email(
            id = 7,
            name = com.example.reply.R.string.recommendation_7_name,
            description = com.example.reply.R.string.recommendation_7_description,
            imageUrl = com.example.reply.R.drawable.avatar_2,
            mailbox = MailboxType.Sent
        ),
        Email(
            id = 8,
            name = com.example.reply.R.string.recommendation_8_name,
            description = com.example.reply.R.string.recommendation_8_description,
            imageUrl = com.example.reply.R.drawable.avatar_2,
            mailbox = MailboxType.Sent
        ),
        Email(
            id = 9,
            name = com.example.reply.R.string.recommendation_9_name,
            description = com.example.reply.R.string.recommendation_9_description,
            imageUrl = com.example.reply.R.drawable.avatar_2,
            mailbox = MailboxType.Sent
        ),
        Email(
            id = 10,
            name = com.example.reply.R.string.recommendation_10_name,
            description = com.example.reply.R.string.recommendation_10_description,
            imageUrl = com.example.reply.R.drawable.avatar_2,
            mailbox = MailboxType.Sent
        ),
        Email(
            id = 11,
            name = com.example.reply.R.string.recommendation_11_name,
            description = com.example.reply.R.string.recommendation_11_description,
            imageUrl = com.example.reply.R.drawable.avatar_2,
            mailbox = MailboxType.Sent
        ),
        Email(
            id = 12,
            name = com.example.reply.R.string.recommendation_13_name,
            description = com.example.reply.R.string.recommendation_13_description,
            imageUrl = com.example.reply.R.drawable.avatar_2,
            mailbox = MailboxType.Sent
        ),
        Email(
            id = 13,
            name = com.example.reply.R.string.recommendation_14_name,
            description = com.example.reply.R.string.recommendation_14_description,
            imageUrl = com.example.reply.R.drawable.avatar_2,
            mailbox = MailboxType.Sent
        ),
        Email(
            id = 14,
            name = com.example.reply.R.string.recommendation_12_name,
            description = com.example.reply.R.string.recommendation_12_description,
            imageUrl = com.example.reply.R.drawable.avatar_2,
            mailbox = MailboxType.Drafts
        ),
        Email(
            id = 15,
            name = com.example.reply.R.string.recommendation_15_name,
            description = com.example.reply.R.string.recommendation_15_description,
            imageUrl = com.example.reply.R.drawable.avatar_2,
            mailbox = MailboxType.Drafts
        ),
        Email(
            id = 16,
            name = com.example.reply.R.string.recommendation_16_name,
            description = com.example.reply.R.string.recommendation_16_description,
            imageUrl = com.example.reply.R.drawable.avatar_2,
            mailbox = MailboxType.Drafts
        ),
        Email(
            id = 17,
            name = com.example.reply.R.string.recommendation_17_name,
            description = com.example.reply.R.string.recommendation_17_description,
            imageUrl = com.example.reply.R.drawable.avatar_2,
            mailbox = MailboxType.Drafts
        ),
        Email(
            id = 18,
            name = com.example.reply.R.string.recommendation_18_name,
            description = com.example.reply.R.string.recommendation_18_description,
            imageUrl = com.example.reply.R.drawable.avatar_2,
            mailbox = MailboxType.Drafts
        ),
        Email(
            id = 19,
            name = com.example.reply.R.string.recommendation_19_name,
            description = com.example.reply.R.string.recommendation_19_description,
            imageUrl = com.example.reply.R.drawable.avatar_2,
            mailbox = MailboxType.Drafts
        ),
        Email(
            id = 20,
            name = com.example.reply.R.string.recommendation_20_name,
            description = com.example.reply.R.string.recommendation_20_description,
            imageUrl = com.example.reply.R.drawable.avatar_2,
            mailbox = MailboxType.Drafts
        )
    )

    /**
     * Get an [Email] with the given [id].
     */
    fun get(id: Long): Email? {
        return allEmails.firstOrNull { it.id == id }
    }

    val defaultEmail = Email(
        id = 20,
        name = com.example.reply.R.string.recommendation_20_name,
        description = com.example.reply.R.string.recommendation_20_description,
        imageUrl = com.example.reply.R.drawable.avatar_2,
        mailbox = MailboxType.Drafts
    )
}
