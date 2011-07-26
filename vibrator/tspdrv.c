/*
 * Copyright (C) 2011 CyanogenMod Project
 * Copyright (C) 2011 Daniel Hillenbrand
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#include <unistd.h>
#include <fcntl.h>
#include <errno.h>
#include <sys/ioctl.h>

#define LOG_NDEBUG 0
#define LOG_TAG "tspdrv"
#include <utils/Log.h>

#include "tspdrv.h"

int sendit(int timeout_ms)
{
    int nwr, ret, fd, tspd, tspret, actuators;
    char value[20];

    tspd = open(TSPDRV_DEVICE, O_RDWR);
    if(tspd < 0) {
        LOGE("failed on opening /dev/tspdrv");
    } else {
        LOGV("opened device /dev/tspdrv");
    }

    /* send tspdrv magic number */
    tspret = ioctl(tspd, TSPDRV_MAGIC_NUMBER);
    if(tspret != 0) {
        LOGE("TSPDRV_MAGIC_NUMBER error");
    } else {
        LOGV("sent TSPDRV_MAGIC_NUMBER");
    }

    /* get number of actuators */
    actuators = ioctl(tspd, TSPDRV_GET_NUM_ACTUATORS);
    if(actuators < 0) {
        LOGE("TSPDRV_GET_NUM_ACTUATORS error, no actuators available");
    } else {
        LOGV("TSPDRV_GET_NUM_ACTUATORS success, actuators = %d", actuators);

        /* enable tspdrv amp */
        tspret = ioctl(tspd, TSPDRV_ENABLE_AMP, actuators);
        if(tspret != 0) {
            LOGE("failed on enabling AMP");
        } else {
            LOGV("enabled AMP");
        }
    }

    fd = open(THE_DEVICE, O_RDWR);
    if(fd < 0)
        return errno;

    nwr = sprintf(value, "%d\n", timeout_ms);
    ret = write(fd, value, nwr);

    close(fd);

    /* stop tspdrv kernel timer */
    tspret = ioctl(tspd, TSPDRV_STOP_KERNEL_TIMER);
    if(tspret != 0) {
        LOGE("failed on stopping kernel timer");
    } else {
        LOGV("stopped kernel timer");
    }

    close(tspd);

    return (ret == nwr) ? 0 : -1;
}
