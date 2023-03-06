package com.barges.dialog_manager

import java.util.UUID

data class DialogConfig(
    val strategy: DialogStrategy,
    val args: Any? = null,
    val id: String = UUID.randomUUID().toString()
)