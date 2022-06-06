package com.gitlab.bank.infra.operation.rest

import java.time.LocalDateTime

object ClockFake {
    private var dateIndex = 0
    val dates = listOf(
            LocalDateTime.of(1975, 2, 17, 12, 7, 0),
            LocalDateTime.of(1979, 7, 27, 12, 7, 0),
            LocalDateTime.of(1980, 7, 25, 12, 7, 0)
    )
    operator fun invoke(): LocalDateTime {
        return dates[dateIndex++ % 3]
    }
}
