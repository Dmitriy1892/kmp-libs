package io.github.dmitriy1892.kmp.libs.utils.datetime

import kotlinx.datetime.Clock

const val ONE_MONTH_MILLIS = 2629800000L
const val ONE_DAY_MILLIS = 86400000L
const val ONE_HOUR_MILLIS = 3600000L
const val ONE_MINUTE_MILLIS = 60000L
const val ONE_SECOND_MILLIS = 1000L

const val EPOCH_MILLIS_START_TIME = 0L

/**
 * Function that returns a current time in epoch timestamp milliseconds format.
 *
 * @return [Long] - current time in epoch timestamp milliseconds format.
 */
fun currentTimeMillis(): Long =
    Clock.System.now().toEpochMilliseconds()